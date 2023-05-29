package com.example.ecommerce.application.port.product.out

import com.example.ecommerce.domain.product.ProductCategory
import com.example.ecommerce.domain.product.ProductCategoryId

interface ProductCategoryQueryPort {

    fun findById(productCategoryId: ProductCategoryId): ProductCategory?

    fun findAll(): List<ProductCategory>

    fun existsByName(name: String) : Boolean

    fun existsByCode(code: String) : Boolean
}
