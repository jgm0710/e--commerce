package com.example.ecommerce.adepter.`in`.web.member.response

import com.example.ecommerce.domain.member.MemberAddress

data class AddressResponse(
    val address: String,
    val detailAddress: String,
    val zipCode: String
) {

    companion object {
        fun of(it: MemberAddress): AddressResponse {
            return AddressResponse(
                address = it.address,
                detailAddress = it.detailAddress,
                zipCode = it.zipCode
            )
        }
    }
}