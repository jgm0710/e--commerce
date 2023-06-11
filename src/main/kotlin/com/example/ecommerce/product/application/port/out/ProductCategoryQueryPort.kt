package com.example.ecommerce.product.application.port.out

import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductCategoryId
import com.example.ecommerce.global.pagination.PageQuery

interface ProductCategoryQueryPort {

    fun findById(productCategoryId: ProductCategoryId): ProductCategory?

    fun findAll(): List<ProductCategory>

    fun findAllBy(
        productCategoryId: ProductCategoryId?,
        name: String?,
        code: String?,
        pageQuery: PageQuery
    ): List<ProductCategory>

    fun existsByName(name: String): Boolean

    fun existsByCode(code: String): Boolean
}
