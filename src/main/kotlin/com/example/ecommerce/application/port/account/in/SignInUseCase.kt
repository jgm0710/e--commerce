package com.example.ecommerce.application.port.account.`in`

import com.example.ecommerce.application.port.account.`in`.command.SignInCommand
import com.example.ecommerce.domain.account.AuthInfo

interface SignInUseCase {

    fun signIn(command: SignInCommand): AuthInfo
}
