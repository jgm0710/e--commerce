package com.example.ecommerce.adepter.out.persistence.product

import com.example.ecommerce.adepter.out.persistence.common.BaseEntity
import com.example.ecommerce.domain.product.ProductCategory
import com.example.ecommerce.domain.product.ProductCategoryId
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