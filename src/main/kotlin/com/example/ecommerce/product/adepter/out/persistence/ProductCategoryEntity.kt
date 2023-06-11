package com.example.ecommerce.product.adepter.out.persistence

import com.example.ecommerce.global.persistence.BaseEntity
import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductCategoryId
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ProductCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val code: String,
    createdAt: Instant,
    lastModifiedAt: Instant,
) : BaseEntity(createdAt = createdAt, lastModifiedAt = lastModifiedAt) {

    fun toDomain(): ProductCategory {
        return ProductCategory(
            name = name,
            code = code
        ).also {
            it.id = ProductCategoryId(checkNotNull(id))
            it.createdAt = createdAt
            it.lastModifiedAt = lastModifiedAt
        }
    }

    companion object {
        fun ProductCategory.toEntity(): ProductCategoryEntity {
            return ProductCategoryEntity(
                id = savedId,
                name = name,
                code =code,
                createdAt = createdAt,
                lastModifiedAt = lastModifiedAt
            )
        }
    }
}