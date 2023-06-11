package com.example.ecommerce.user.adepter.`in`.web.member.request

import com.example.ecommerce.user.domain.member.Agreement
import com.example.ecommerce.user.domain.member.AgreementType

data class AgreementRequest(
    val agreementType: AgreementType,
    val isAccepted: Boolean,
) {

    fun toDomain(): Agreement = Agreement(
        agreementType = agreementType, isAccepted = isAccepted
    )
}