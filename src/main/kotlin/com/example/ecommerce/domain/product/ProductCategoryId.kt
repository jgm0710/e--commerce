package com.example.ecommerce.domain.product

import com.example.ecommerce.global.domain.AggregateId

data class ProductCategoryId(
    override val value: Long
) : AggregateId