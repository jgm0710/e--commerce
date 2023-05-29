package com.example.ecommerce.domain.product

import com.example.ecommerce.global.domain.AbstractAggregate

data class ProductCategory(
    val name: String,
) : AbstractAggregate<ProductCategoryId>() {

    fun modify(name: String): ProductCategory {
        return copy(name = name)
    }
}