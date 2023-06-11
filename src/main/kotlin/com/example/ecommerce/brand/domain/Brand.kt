package com.example.ecommerce.brand.domain

import com.example.ecommerce.global.domain.AbstractAggregate

data class Brand(
    val imageUrl: String,
    val name: String,
    val introduction: String,
) : AbstractAggregate<BrandId>() {
}