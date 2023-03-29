package com.example.ecommerce.domain.member

import com.example.ecommerce.global.domain.AbstractAggregate
import java.time.LocalDate

class Member(
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val memberAddress: MemberAddress,
) : AbstractAggregate<MemberId>()
