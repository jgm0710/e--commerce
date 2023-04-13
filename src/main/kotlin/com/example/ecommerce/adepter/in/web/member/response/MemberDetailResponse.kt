package com.example.ecommerce.adepter.`in`.web.member.response

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.MemberAddress
import com.example.ecommerce.domain.member.model.MemberDetail
import java.time.LocalDate

data class MemberDetailResponse(
    val id: Long,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val agreements: List<Agreement>,
    val address: Address
) {

    data class Address(val address: String,
                       val detailAddress: String,
                       val zipCode: String) {
        companion object {
            fun from(it: MemberAddress): Address {
                return Address(address = it.address,
                    detailAddress = it.detailAddress,
                    zipCode = it.zipCode)
            }
        }
    }

    companion object {
        fun from(memberDetail: MemberDetail): MemberDetailResponse {
            return MemberDetailResponse(
                id = memberDetail.id.value,
                name = memberDetail.name,
                nickname = memberDetail.nickname,
                birth = memberDetail.birth,
                email = memberDetail.email,
                phone = memberDetail.phone,
                tel = memberDetail.tel,
                agreements = memberDetail.agreements,
                address = memberDetail.memberAddress.let { Address.from(it) })
        }
    }
}
