package com.example.ecommerce.brand.adepter.out.persistence

import com.example.ecommerce.global.persistence.BaseEntity
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "brand")
class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val imageUrl: String,
    val name: String,
    val introduction: String,
    createdAt: Instant, lastModifiedAt: Instant
) : BaseEntity(createdAt = createdAt, lastModifiedAt = lastModifiedAt)