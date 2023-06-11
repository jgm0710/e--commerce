package com.example.ecommerce.user.adepter.out.persistence.mebmer

import com.example.ecommerce.user.domain.member.Agreement
import com.example.ecommerce.user.domain.member.AgreementType
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
    fun toDomain(): Agreement {
        return Agreement(
            agreementType = agreementType,
            isAccepted = isAccepted
        )
    }

    companion object {
        fun Agreement.toEntity(): AgreementElementCollection {
            return AgreementElementCollection(agreementType = agreementType, isAccepted = isAccepted)
        }
    }
}
