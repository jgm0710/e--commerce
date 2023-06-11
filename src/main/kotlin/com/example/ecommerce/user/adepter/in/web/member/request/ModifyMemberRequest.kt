package com.example.ecommerce.user.adepter.`in`.web.member.request

import com.example.ecommerce.user.application.port.`in`.member.command.ModifyMemberCommand
import com.example.ecommerce.user.domain.member.MemberId
import java.time.LocalDate

data class ModifyMemberRequest(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val memberAddress: MemberAddressRequest,
) {
    fun toCommand(memberId: Long): ModifyMemberCommand = ModifyMemberCommand(
        memberId = MemberId(memberId),
        name = name,
        nickname = nickname,
        birth = birth,
        email = email,
        phone = phone,
        tel = tel,
        memberAddress = memberAddress.toDomain()
    )
}
