package com.example.ecommerce.application.service

import com.example.ecommerce.application.port.account.out.SaveAccountPort
import com.example.ecommerce.application.port.member.`in`.*
import com.example.ecommerce.application.port.member.`in`.command.ModifyMemberCommand
import com.example.ecommerce.application.port.member.`in`.command.SignUpCommand
import com.example.ecommerce.application.port.member.out.DeleteMemberPort
import com.example.ecommerce.application.port.member.out.FindMemberPort
import com.example.ecommerce.application.port.member.out.SaveMemberPort
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.event.MemberDomainEventPublisher
import com.example.ecommerce.domain.member.exception.MemberNotFoundException
import com.example.ecommerce.domain.member.model.MemberDetail
import com.example.ecommerce.domain.member.model.MemberSimple
import com.example.ecommerce.global.event.ResultWithDomainEvents
import com.example.ecommerce.global.event.domainevent.MemberModifiedEvent
import com.example.ecommerce.global.event.domainevent.MemberSignUpEvent
import com.example.ecommerce.global.pagination.PageQuery
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.jvm.internal.calls.ThrowingCaller.member

@Service
class MemberAplService(
    private val saveMemberPort: SaveMemberPort,
    private val saveAccountPort: SaveAccountPort,
    private val findMemberPort: FindMemberPort,
    private val deleteMemberPort: DeleteMemberPort,
    private val memberDomainEventPublisher: MemberDomainEventPublisher,
) : SignUpUseCase, GetMemberDetailQueryCase, DeleteMemberUseCase, FindMembersQueryCase, ModifyMemberUseCase {

    @Transactional
    override fun signUp(command: SignUpCommand): MemberId {
        return command.createMember()
            .let { signUpResultWithDomainEvents: ResultWithDomainEvents<Member, MemberSignUpEvent> ->
                val savedResult: Member = saveMemberPort.save(signUpResultWithDomainEvents.result)
                saveAccountPort.save(command.createAccount(savedResult.id))
                memberDomainEventPublisher.publish(signUpResultWithDomainEvents.result, signUpResultWithDomainEvents.domainEvents)
                return@let savedResult
            }.id
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

    @Transactional
    override fun deleteMember(memberId: MemberId) {
        deleteMemberPort.deleteById(memberId)
    }

    @Transactional
    override fun modifyMember(command: ModifyMemberCommand) {
        val member: Member = findMemberPort.findById(command.memberId)
            ?: throw MemberNotFoundException(memberId = command.memberId)

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
