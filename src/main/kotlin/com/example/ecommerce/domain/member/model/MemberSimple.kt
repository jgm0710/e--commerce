package com.example.ecommerce.domain.member.model

import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.MemberId
import java.time.LocalDate

data class MemberSimple(
    val id : MemberId,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val address: MemberAddress
) {
    companion object {
        fun from(member: Member) : MemberSimple{
            return MemberSimple(
                id = member.id,
                name = member.name,
                nickname = member.nickname,
                birth = member.birth,
                email = member.email,
                phone = member.phone,
                tel = member.tel,
                address = member.memberAddress)
        }
    }
}
