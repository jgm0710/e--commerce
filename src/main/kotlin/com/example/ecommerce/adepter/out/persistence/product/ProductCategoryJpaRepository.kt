package com.example.ecommerce.adepter.out.persistence.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryJpaRepository : JpaRepository<ProductCategoryEntity, Long> {

    fun existsByName(name: String): Boolean

    fun existsByCode(code: String): Boolean
}