package com.example.ecommerce.product.domain

import com.example.ecommerce.global.domain.AggregateId

data class ProductCategoryId(
    override val value: Long
) : AggregateId