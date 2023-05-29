package com.example.ecommerce.application.port.product.`in`.command

import com.example.ecommerce.domain.product.ProductCategory

data class CreateProductionCategoryCommand(val name: String) {

    fun createProductCategory(): ProductCategory {
        return ProductCategory(
            name = name
        )
    }
}