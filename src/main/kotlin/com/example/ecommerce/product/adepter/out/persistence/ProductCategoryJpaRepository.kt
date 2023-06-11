package com.example.ecommerce.product.adepter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryJpaRepository : JpaRepository<ProductCategoryEntity, Long> {

    fun existsByName(name: String): Boolean

    fun existsByCode(code: String): Boolean
}