package com.example.ecommerce.domain.product

import com.example.ecommerce.global.domain.AbstractAggregate

data class Product(
    val title: String,
    val introduction: String,
    val categoryId: ProductCategoryId,
    val options: List<ProductOption>,
    val titleImageUrl: String,
    val imageUrlList: List<String>,
) : AbstractAggregate<ProductId>()