package com.example.ecommerce.user.adepter.`in`.web.member.response

import com.example.ecommerce.user.domain.member.model.MemberSimple
import java.time.LocalDate

data class MemberSimpleResponse(
    val id: Long,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val email: String,
    val phone: String?,
    val tel: String?,
    val address: AddressResponse
) {

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
                address = member.address.let { AddressResponse.of(it) })
        }
    }
}
