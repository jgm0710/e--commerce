package com.example.ecommerce.user.application.port.`in`.member.command

import com.example.ecommerce.user.domain.member.MemberAddress
import com.example.ecommerce.user.domain.member.MemberId
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
