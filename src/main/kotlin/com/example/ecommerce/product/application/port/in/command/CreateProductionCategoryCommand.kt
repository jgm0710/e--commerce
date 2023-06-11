package com.example.ecommerce.product.application.port.`in`.command

import com.example.ecommerce.product.domain.ProductCategory

data class CreateProductionCategoryCommand(val name: String, val code: String) {

    fun createProductCategory(): ProductCategory {
        return ProductCategory(
            name = name,
            code = code
        )
    }
}