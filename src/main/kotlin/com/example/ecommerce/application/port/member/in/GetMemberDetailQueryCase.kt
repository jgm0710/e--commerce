package com.example.ecommerce.application.port.member.`in`

import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.model.MemberDetail

interface GetMemberDetailQueryCase {

    fun getMemberDetail(memberId: MemberId): MemberDetail
}

operator fun GetMemberDetailQueryCase.invoke(memberId: MemberId) = getMemberDetail(memberId)
