package com.example.ecommerce.adepter.`in`.web.account.request

import com.example.ecommerce.application.port.account.`in`.command.SignInCommand

data class SignInRequest(
    val loginId : String,
    val password : String
) {
    fun toCommand(): SignInCommand {
        TODO("Not yet implemented")
    }
}
