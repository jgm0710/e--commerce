package com.example.ecommerce.product.application.port.`in`

import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductCategoryId
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