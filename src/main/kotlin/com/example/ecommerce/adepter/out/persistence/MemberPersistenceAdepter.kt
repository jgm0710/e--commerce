package com.example.ecommerce.adepter.out.persistence

import com.example.ecommerce.application.port.member.out.FindMemberPort
import com.example.ecommerce.application.port.member.out.SaveMemberPort
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.MemberId
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class MemberPersistenceAdepter(
//    val memberJpaRepository: MemberJpaRepository,
) : SaveMemberPort , FindMemberPort{

    override fun save(member: Member): Member {
        return member
//        return memberJpaRepository.save(member)
    }

    override fun findById(memberId: MemberId): Member? {
        return Member(
            name = "",
            nickname = "",
            birth = LocalDate.now(),
            email = "",
            phone = null,
            tel = null,
            agreements = listOf(),
            memberAddress = MemberAddress(
                address = "",
                detailAddress = "",
                zipCode = ""
            )
        )
//        return memberJpaRepository.findByIdOrNull(memberId)
    }
}
