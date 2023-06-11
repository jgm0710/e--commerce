package com.example.ecommerce.user.application.port.`in`.member

import com.example.ecommerce.user.application.port.`in`.member.command.ModifyMemberCommand

interface ModifyMemberUseCase {

    fun modifyMember(command: ModifyMemberCommand)
}

operator fun ModifyMemberUseCase.invoke(command: ModifyMemberCommand) = modifyMember(command)
