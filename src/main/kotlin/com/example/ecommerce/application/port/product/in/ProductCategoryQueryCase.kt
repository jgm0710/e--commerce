package com.example.ecommerce.application.port.product.`in`

import com.example.ecommerce.domain.product.ProductCategory

interface ProductCategoryQueryCase {

    fun findAll(): List<ProductCategory>
}