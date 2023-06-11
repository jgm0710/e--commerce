package com.example.ecommerce.user.adepter.`in`.web.account.request

import com.example.ecommerce.user.application.port.`in`.account.command.SignInCommand

data class SignInRequest(
    val loginId : String,
    val password : String
) {
    fun toCommand(): SignInCommand {
        return SignInCommand(
            loginId = loginId,
            password = password
        )
    }
}
