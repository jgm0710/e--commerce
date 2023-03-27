package com.example.ecommerce.application.port.member.`in`

import com.example.ecommerce.application.port.member.`in`.command.SignUpCommand
import com.example.ecommerce.domain.member.MemberId

interface SignUpUseCase {

    fun signUp(command: SignUpCommand) : MemberId
}
