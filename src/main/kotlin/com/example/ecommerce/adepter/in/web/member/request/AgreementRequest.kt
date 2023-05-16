package com.example.ecommerce.adepter.`in`.web.member.request

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.AgreementType

data class AgreementRequest(
    val agreementType: AgreementType,
    val isAccepted: Boolean,
) {

    fun toDomain(): Agreement = Agreement(
        agreementType = agreementType, isAccepted = isAccepted
    )
}