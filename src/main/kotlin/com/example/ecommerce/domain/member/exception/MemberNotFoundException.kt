package com.example.ecommerce.domain.member.exception

import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.global.exception.AbstractNotFoundException

class MemberNotFoundException(memberId: MemberId) :
    AbstractNotFoundException("Member Not Found. MemberId : ${memberId.value}")
