package com.example.ecommerce.application.port.member.out

import com.example.ecommerce.domain.member.MemberId

interface DeleteMemberPort {

    fun deleteById(memberId: MemberId)
}
