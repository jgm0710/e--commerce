package com.example.ecommerce.adepter.`in`.web.request

import com.example.ecommerce.application.port.member.`in`.command.SignUpCommand
import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.MemberAddress
import java.time.LocalDate

data class SignUpRequest(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val memberAddress: MemberAddress,
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
            agreements = agreements,
            memberAddress = memberAddress,
            loginId = loginId,
            password = password
        )
    }
}
