package com.example.ecommerce.application.port.member.out

import com.example.ecommerce.domain.member.Member

interface SaveMemberPort {

    fun save(member: Member): Member
}
