package com.example.ecommerce.adepter.`in`.web.member.request

import com.example.ecommerce.application.port.member.`in`.command.ModifyMemberCommand

data class ModifyMemberRequest {
    fun toCommand(memberId: Long): ModifyMemberCommand {
        TODO("Not yet implemented")
    }
}
