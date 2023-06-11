package com.example.ecommerce.user.application.port.`in`.member

import com.example.ecommerce.user.domain.member.MemberId

interface DeleteMemberUseCase {

    fun deleteMember(memberId: MemberId)
}
