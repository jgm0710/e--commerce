package com.example.ecommerce.adepter.out.persistence.member

import com.example.ecommerce.domain.member.Agreement
import com.example.ecommerce.domain.member.AgreementType
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class AgreementElementCollection(
        @Column
        @Enumerated(EnumType.STRING)
        val agreementType: AgreementType,
        @Column
        val isAccepted: Boolean,
) {

    companion object {
        fun Agreement.toEntity(): AgreementElementCollection {
            return AgreementElementCollection(agreementType = agreementType, isAccepted = isAccepted)
        }
    }
}
