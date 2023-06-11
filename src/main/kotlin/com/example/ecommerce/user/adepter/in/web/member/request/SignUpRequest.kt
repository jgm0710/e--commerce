package com.example.ecommerce.user.adepter.`in`.web.member.request

import com.example.ecommerce.user.application.port.`in`.member.command.SignUpCommand
import java.time.LocalDate

data class SignUpRequest(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<AgreementRequest>,
    val memberAddress: MemberAddressRequest,
    val loginId: String,
    val password: String,
) {
    fun toCommand(): SignUpCommand {
        return SignUpCommand(
            name = name,
            nickname = nickname,
            birth = birth,
            email = email,
            phone = phone,
            tel = tel,
            agreements = agreements.map { it.toDomain() },
            memberAddress = memberAddress.toDomain(),
            loginId = loginId,
            password = password
        )
    }
}
