package com.example.ecommerce.user.application.port.out.member

import com.example.ecommerce.user.domain.member.Member

interface SaveMemberPort {

    fun save(member: Member): Member
}
