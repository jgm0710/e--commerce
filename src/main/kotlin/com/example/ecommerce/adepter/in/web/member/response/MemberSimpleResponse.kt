package com.example.ecommerce.adepter.`in`.web.member.response

import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.model.MemberSimple
import java.time.LocalDate

data class MemberSimpleResponse(
    val id: Long,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val address: Address
) {

    data class Address (
        val address: String,
        val detailAddress: String,
        val zipCode: String,
    ) {
        companion object {
            fun from(memberAddress: MemberAddress): Address {
                return Address(
                    address = memberAddress.address,
                    detailAddress = memberAddress.detailAddress,
                    zipCode = memberAddress.zipCode
                )
            }
        }
    }

    companion object {
        fun from(member: MemberSimple): MemberSimpleResponse {
            return MemberSimpleResponse(
                id = member.id.value,
                name = member.name,
                nickname = member.nickname,
                birth = member.birth,
                email = member.email,
                phone = member.phone,
                tel = member.tel,
                address = member.address.let { Address.from(it) })
        }
    }
}
