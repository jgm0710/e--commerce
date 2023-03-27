package com.example.ecommerce.domain.member

import com.example.ecommerce.global.domain.Aggregate
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
) : Aggregate() {

    lateinit var id: MemberId
}
