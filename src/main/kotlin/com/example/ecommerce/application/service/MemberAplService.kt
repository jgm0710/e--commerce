package com.example.ecommerce.application.service

import com.example.ecommerce.application.port.member.`in`.GetMemberDetailQueryCase
import com.example.ecommerce.application.port.member.`in`.SignUpUseCase
import com.example.ecommerce.application.port.member.`in`.command.SignUpCommand
import com.example.ecommerce.application.port.member.out.FindMemberPort
import com.example.ecommerce.application.port.member.out.SaveMemberPort
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.exception.MemberNotFoundException
import com.example.ecommerce.domain.member.model.MemberDetail
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAplService(
    private val saveMemberPort: SaveMemberPort,
//    private val saveAccountPort: SaveAccountPort,
    private val findMemberPort: FindMemberPort,
    private val applicationEventPublisher: ApplicationEventPublisher
) : SignUpUseCase, GetMemberDetailQueryCase {

    @Transactional
    override fun signUp(command: SignUpCommand): MemberId {
        val member = command.createMember()
        val savedMember: Member = saveMemberPort.save(member)
//        saveAccountPort.save(command.createAccount(savedMember.id))
        applicationEventPublisher.publishEvent(member)
        return savedMember.id
    }

    @Transactional(readOnly = true)
    override fun getMemberDetail(memberId: MemberId): MemberDetail {
        val member = findMemberPort.findById(memberId) ?: throw MemberNotFoundException(memberId)
        return MemberDetail.from(member)
    }
}
