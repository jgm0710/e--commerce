package com.example.ecommerce.adepter.`in`.web.member.response

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.AgreementType

data class AgreementResponse(
    val agreementType: AgreementType,
    val isAccepted: Boolean,
) {

    companion object {
        fun of(agreement: Agreement): AgreementResponse {
            return AgreementResponse(
                agreementType = agreement.agreementType,
                isAccepted = agreement.isAccepted
            )
        }
    }
}
