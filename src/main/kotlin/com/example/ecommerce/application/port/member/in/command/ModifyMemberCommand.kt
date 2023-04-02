package com.example.ecommerce.application.port.member.`in`.command

import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.MemberId
import java.time.LocalDate

data class ModifyMemberCommand(
    val memberId: MemberId,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val memberAddress: MemberAddress,
)
