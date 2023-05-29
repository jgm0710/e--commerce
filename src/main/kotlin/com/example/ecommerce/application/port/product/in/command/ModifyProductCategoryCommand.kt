package com.example.ecommerce.application.port.product.`in`.command

import com.example.ecommerce.domain.product.ProductCategoryId


data class ModifyProductCategoryCommand(
    val productCategoryId: ProductCategoryId,
    val name: String,
    val code: String
)