package com.example.ecommerce.domain.member

import com.example.ecommerce.global.domain.AbstractAggregate
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.LocalDate

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type"
)
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
