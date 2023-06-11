package com.example.ecommerce.user.adepter.`in`.web.member.request

import com.example.ecommerce.user.domain.member.MemberAddress

data class MemberAddressRequest(
    val address: String,
    val detailAddress: String,
    val zipCode: String,
) {

    fun toDomain(): MemberAddress {
        return MemberAddress(
            address = address,
            detailAddress = detailAddress,
            zipCode = zipCode,
        )
    }
}