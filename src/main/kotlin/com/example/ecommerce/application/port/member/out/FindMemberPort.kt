package com.example.ecommerce.application.port.member.out

import com.example.ecommerce.domain.member.Member
import com.example.ecommerce.domain.member.MemberId

interface FindMemberPort {

    fun findById(memberId: MemberId): Member?
}
