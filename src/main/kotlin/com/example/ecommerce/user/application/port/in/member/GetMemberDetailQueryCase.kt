package com.example.ecommerce.user.application.port.`in`.member

import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.user.domain.member.model.MemberDetail

interface GetMemberDetailQueryCase {

    fun getMemberDetail(memberId: MemberId): MemberDetail
}

operator fun GetMemberDetailQueryCase.invoke(memberId: MemberId) = getMemberDetail(memberId)
