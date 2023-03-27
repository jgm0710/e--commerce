package com.example.ecommerce.application.port.member.`in`

import com.example.ecommerce.domain.member.MemberId

interface DeleteMemberUseCase {

    fun deleteMember(memberId: MemberId)
}
