package com.example.ecommerce.adepter.`in`.eventhandler

import com.example.ecommerce.domain.member.Member
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MemberEventHandler {

    @KafkaListener(

    )
    fun handleEvent(member: Member) {

//        jacksonObjectMapper().setSubtypeResolver()

//        println(jacksonObjectMapper().writeValueAsString(member))
//        val writerWithDefaultPrettyPrinter = jacksonObjectMapper()
//            .writerWithDefaultPrettyPrinter()
//            .write
    }
}
