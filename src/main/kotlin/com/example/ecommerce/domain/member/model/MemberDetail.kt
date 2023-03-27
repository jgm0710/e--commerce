package com.example.ecommerce.domain.member.model

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.MemberId
import java.time.LocalDate

data class MemberDetail(
    val id: MemberId,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val memberAddress: MemberAddress
) {
    companion object {
        fun from(member: Member) : MemberDetail{
            TODO("Not yet implemented")
        }
    }
}
