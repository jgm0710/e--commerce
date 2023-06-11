package com.example.ecommerce.user.domain.member.exception

import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.global.exception.AbstractNotFoundException

class MemberNotFoundException(memberId: MemberId) : AbstractNotFoundException(MemberErrorCode.MEMBER_NOT_FOUND) {
    override val message: String = "Member Not Found. MemberId : ${memberId.value}"
}
