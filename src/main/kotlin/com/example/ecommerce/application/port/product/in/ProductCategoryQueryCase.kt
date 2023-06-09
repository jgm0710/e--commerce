package com.example.ecommerce.application.port.product.`in`

import com.example.ecommerce.domain.product.ProductCategory
import com.example.ecommerce.domain.product.ProductCategoryId
import com.example.ecommerce.global.pagination.PageQuery

interface ProductCategoryQueryCase {

    fun findAll(
        productCategoryId: ProductCategoryId?,
        name: String?,
        code: String?,
        pageQuery: PageQuery
    ): List<ProductCategory>

    companion object {
        operator fun ProductCategoryQueryCase.invoke(
            productCategoryId: ProductCategoryId?,
            name: String?,
            code: String?,
            pageQuery: PageQuery
        ) = findAll(
            productCategoryId = productCategoryId,
            name = name,
            code = code,
            pageQuery = pageQuery
        )
    }
}