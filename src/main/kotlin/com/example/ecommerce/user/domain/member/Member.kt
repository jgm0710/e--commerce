package com.example.ecommerce.user.domain.member

import com.example.ecommerce.global.domain.AbstractAggregate
import com.example.ecommerce.global.event.ResultWithDomainEvents
import com.example.ecommerce.global.event.domainevent.MemberModifiedEvent
import java.time.LocalDate

data class Member(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val memberAddress: MemberAddress,
) : AbstractAggregate<MemberId>() {

    fun modifyInfo(
        name: String,
        nickname: String,
        birth: LocalDate,
        email: String,
        phone: String?,
        tel: String?,
        memberAddress: MemberAddress,
    ): ResultWithDomainEvents<Member, MemberModifiedEvent> {
        val modifiedMember = copy(
            name = name,
            nickname = nickname,
            birth = birth,
            email = email,
            phone = phone,
            tel = tel,
            memberAddress = memberAddress,
        )

        return ResultWithDomainEvents(modifiedMember, MemberModifiedEvent.from(modifiedMember))
    }

    fun neNickname(nickname: String): Boolean {
        return this.nickname != nickname
    }
}
