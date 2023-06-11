package com.example.ecommerce.user.application.port.`in`.account.command

data class SignInCommand(
    val loginId: String,
    val password: String
)
