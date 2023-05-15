package com.example.ecommerce.application.port.member.`in`

import com.example.ecommerce.application.port.member.`in`.command.ModifyMemberCommand

interface ModifyMemberUseCase {

    fun modifyMember(command: ModifyMemberCommand)
}

operator fun ModifyMemberUseCase.invoke(command: ModifyMemberCommand) = modifyMember(command)
