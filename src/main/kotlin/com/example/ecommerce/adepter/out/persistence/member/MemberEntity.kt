package com.example.ecommerce.adepter.out.persistence.member

import com.example.ecommerce.adepter.out.persistence.member.AgreementElementCollection.Companion.toEntity
import com.example.ecommerce.domain.member.Member
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val nickname: String,

        @Column(nullable = false)
        val birth: LocalDate,

        @Column(nullable = false)
        val email: String,

        @Column
        val phone: String?,

        @Column
        val tel: String?,

        @Column
        val address: String,

        @Column
        val detailAddress: String,

        @Column
        val zipCode: String,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "member_agreement", joinColumns = [JoinColumn(name = "member_id")])
        val agreements: List<AgreementElementCollection>,
) {

    companion object {
        fun Member.toEntity(): MemberEntity {
            return MemberEntity(
                    id = id.value,
                    name = name,
                    nickname = nickname,
                    birth = birth,
                    email = email,
                    phone = phone,
                    tel = tel,
                    address = memberAddress.address,
                    detailAddress = memberAddress.detailAddress,
                    zipCode = memberAddress.zipCode,
                    agreements = agreements.map { it.toEntity() }
            )
        }
    }
}
