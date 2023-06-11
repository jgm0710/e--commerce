package com.example.ecommerce.product.application.port.`in`.command

import com.example.ecommerce.product.domain.ProductCategoryId


data class ModifyProductCategoryCommand(
    val productCategoryId: ProductCategoryId,
    val name: String,
    val code: String
)