package com.example.ecommerce.user.application.port.`in`.member

import com.example.ecommerce.user.application.port.`in`.member.command.SignUpCommand
import com.example.ecommerce.user.domain.member.MemberId

interface SignUpUseCase {

    fun signUp(command: SignUpCommand) : MemberId
}

operator fun SignUpUseCase.invoke(command: SignUpCommand) = signUp(command)
