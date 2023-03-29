package com.example.ecommerce.global.event.domainevent

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberAddress
import java.time.LocalDate

sealed class MemberDomainEvent(
        open val name: String,
        open val nickname: String,
        open val birth: LocalDate,
        open val email: String,
        open val phone: String?,
        open val tel: String?,
        open val agreements: List<Agreement>,
        open val memberAddress: MemberAddress,
) : DomainEvent

data class MemberSignUpEvent(
        override val name: String,
        override val nickname: String,
        override val birth: LocalDate,
        override val email: String,
        override val phone: String?,
        override val tel: String?,
        override val agreements: List<Agreement>,
        override val memberAddress: MemberAddress
) : MemberDomainEvent(
        name = name,
        nickname = nickname,
        birth = birth,
        email = email,
        phone = phone,
        tel = tel,
        agreements = agreements,
        memberAddress = memberAddress
) {

    companion object {
        fun from(member: Member): MemberSignUpEvent {
            return MemberSignUpEvent(
                    name = member.name,
                    nickname = member.nickname,
                    birth = member.birth,
                    email = member.email,
                    phone = member.phone,
                    tel = member.tel,
                    agreements = member.agreements,
                    memberAddress = member.memberAddress
            )
        }
    }
}

