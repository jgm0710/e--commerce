package com.example.ecommerce.user.application.port.`in`.account

import com.example.ecommerce.user.application.port.`in`.account.command.SignInCommand
import com.example.ecommerce.user.domain.account.AuthInfo

interface SignInUseCase {

    fun signIn(command: SignInCommand): AuthInfo
}
