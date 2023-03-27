package com.example.ecommerce.adepter.`in`.eventhandler

import com.example.ecommerce.domain.member.Member
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MemberEventHandler {

    @EventListener(Member::class)
    fun handleEvent(member: Member) {
        println(jacksonObjectMapper().writeValueAsString(member))
    }
}
