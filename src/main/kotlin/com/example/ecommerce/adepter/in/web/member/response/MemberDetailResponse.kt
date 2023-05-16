package com.example.ecommerce.adepter.`in`.web.member.response

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
    val agreements: List<AgreementResponse>,
    val address: AddressResponse
) {

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
                agreements = memberDetail.agreements.map { AgreementResponse.of(it) },
                address = memberDetail.memberAddress.let { AddressResponse.of(it) })
        }
    }
}
