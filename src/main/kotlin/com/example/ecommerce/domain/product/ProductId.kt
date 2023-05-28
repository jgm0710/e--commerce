package com.example.ecommerce.domain.product

import com.example.ecommerce.global.domain.AggregateId

data class ProductId(override val value: Long) :AggregateId {
}