package com.example.ecommerce.domain.product

import com.example.ecommerce.global.domain.AbstractAggregate

data class ProductCategory(
    val name: String,
    val code: String,
) : AbstractAggregate<ProductCategoryId>() {

    fun modify(name: String, code: String): ProductCategory {
        return copy(name = name, code = code)
    }

    fun eqName(name: String): Boolean {
        return this.name == name
    }

    fun eqCode(code: String): Boolean {
        return this.code==code
    }
}