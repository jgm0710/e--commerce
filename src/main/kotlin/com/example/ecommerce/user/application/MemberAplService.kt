package com.example.ecommerce.user.application

import com.example.ecommerce.global.event.ResultWithDomainEvents
import com.example.ecommerce.global.event.domainevent.MemberModifiedEvent
import com.example.ecommerce.global.event.domainevent.MemberSignUpEvent
import com.example.ecommerce.global.pagination.PageQuery
import com.example.ecommerce.user.application.port.`in`.member.*
import com.example.ecommerce.user.application.port.`in`.member.command.ModifyMemberCommand
import com.example.ecommerce.user.application.port.`in`.member.command.SignUpCommand
import com.example.ecommerce.user.application.port.out.account.FindAccountPort
import com.example.ecommerce.user.application.port.out.account.SaveAccountPort
import com.example.ecommerce.user.application.port.out.member.DeleteMemberPort
import com.example.ecommerce.user.application.port.out.member.FindMemberPort
import com.example.ecommerce.user.application.port.out.member.SaveMemberPort
import com.example.ecommerce.user.domain.account.AuthenticationProvider
import com.example.ecommerce.user.domain.account.exception.DuplicateLoginIdException
import com.example.ecommerce.user.domain.member.Member
import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.user.domain.member.event.MemberDomainEventPublisher
import com.example.ecommerce.user.domain.member.exception.DuplicateNicknameException
import com.example.ecommerce.user.domain.member.exception.MemberNotFoundException
import com.example.ecommerce.user.domain.member.model.MemberDetail
import com.example.ecommerce.user.domain.member.model.MemberSimple
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAplService(
    private val saveMemberPort: SaveMemberPort,
    private val saveAccountPort: SaveAccountPort,
    private val findAccountPort: FindAccountPort,
    private val authenticationProvider: AuthenticationProvider,
    private val findMemberPort: FindMemberPort,
    private val deleteMemberPort: DeleteMemberPort,
    private val memberDomainEventPublisher: MemberDomainEventPublisher,
) : SignUpUseCase, GetMemberDetailQueryCase, DeleteMemberUseCase, FindMembersQueryCase, ModifyMemberUseCase {

    // TODO: 회원가입 -> 배송지 정보 갱신 처리 진행
    @Transactional
    override fun signUp(command: SignUpCommand): MemberId {
        checkDuplicateNickname(command.nickname)
        checkDuplicateLoginId(command.loginId)

        val member: Member = command.createMember().let { saveMemberPort.save(it) }
        saveAccountPort.save(command.createAccount(member.id, authenticationProvider::encodePassword))
        memberDomainEventPublisher.publish(member, MemberSignUpEvent.from(member))
        return member.id
    }

    private fun checkDuplicateNickname(nickname: String) {
        if (findMemberPort.existsByNickname(nickname)) {
            throw DuplicateNicknameException(nickname)
        }
    }

    private fun checkDuplicateLoginId(loginId : String) {
        if (findAccountPort.existsByLoginId(loginId)) {
            throw DuplicateLoginIdException(loginId)
        }
    }

    @Transactional(readOnly = true)
    override fun getMemberDetail(memberId: MemberId): MemberDetail {
        val member = findMemberPort.findById(memberId) ?: throw MemberNotFoundException(memberId)
        return MemberDetail.from(member)
    }

    @Transactional(readOnly = true)
    override fun findAll(pageQuery: PageQuery): List<MemberSimple> {
        return findMemberPort.findAll(pageQuery).map { MemberSimple.from(it) }
    }

    // TODO: member deleted event 발행 로직 추가. 회원 계정 정보 삭제 처리 필요
    @Transactional
    override fun deleteMember(memberId: MemberId) {
        deleteMemberPort.deleteById(memberId)
    }

    // TODO: 회원 수정 -> 배송지 정보 갱신 처리 진행
    @Transactional
    override fun modifyMember(command: ModifyMemberCommand) {
        val member: Member = findMemberPort.findById(command.memberId)
            ?: throw MemberNotFoundException(memberId = command.memberId)

        if (member.neNickname(command.nickname)) {
            checkDuplicateNickname(command.nickname)
        }

        member.modifyInfo(
            name = command.name,
            nickname = command.nickname,
            birth = command.birth,
            email = command.email,
            phone = command.phone,
            tel = command.tel,
            memberAddress = command.memberAddress
        ).let { modifiedResultWithDomainEvents: ResultWithDomainEvents<Member, MemberModifiedEvent> ->
            saveMemberPort.save(modifiedResultWithDomainEvents.result)
            memberDomainEventPublisher.publish(modifiedResultWithDomainEvents.result, modifiedResultWithDomainEvents.domainEvents)
        }
    }
}
