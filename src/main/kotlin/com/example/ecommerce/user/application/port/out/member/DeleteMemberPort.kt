package com.example.ecommerce.user.application.port.out.member

import com.example.ecommerce.user.domain.member.MemberId

interface DeleteMemberPort {

    fun deleteById(memberId: MemberId)
}
