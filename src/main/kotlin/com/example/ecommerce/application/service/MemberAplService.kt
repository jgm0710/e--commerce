package com.example.ecommerce.application.service

import com.example.ecommerce.application.port.account.out.SaveAccountPort
import com.example.ecommerce.application.port.member.`in`.GetMemberDetailQueryCase
import com.example.ecommerce.application.port.member.`in`.SignUpUseCase
import com.example.ecommerce.application.port.member.`in`.command.SignUpCommand
import com.example.ecommerce.application.port.member.out.FindMemberPort
import com.example.ecommerce.application.port.member.out.SaveMemberPort
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.event.MemberDomainEventPublisher
import com.example.ecommerce.domain.member.exception.MemberNotFoundException
import com.example.ecommerce.domain.member.model.MemberDetail
import com.example.ecommerce.global.event.ResultWithDomainEvents
import com.example.ecommerce.global.event.domainevent.MemberSignUpEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAplService(
        private val saveMemberPort: SaveMemberPort,
        private val saveAccountPort: SaveAccountPort,
        private val findMemberPort: FindMemberPort,
        private val memberDomainEventPublisher: MemberDomainEventPublisher,
) : SignUpUseCase, GetMemberDetailQueryCase {

    @Transactional
    override fun signUp(command: SignUpCommand): MemberId {
        val resultWithDomainEvents: ResultWithDomainEvents<Member, MemberSignUpEvent> = command.createMember()
        val member: Member = resultWithDomainEvents.result.let { saveMemberPort.save(it) }
        command.createAccount(member.id).let { saveAccountPort.save(it) }
        memberDomainEventPublisher.publish(member, resultWithDomainEvents.domainEvents)
        return member.id
    }

    @Transactional(readOnly = true)
    override fun getMemberDetail(memberId: MemberId): MemberDetail {
        val member = findMemberPort.findById(memberId) ?: throw MemberNotFoundException(memberId)
        return MemberDetail.from(member)
    }
}
