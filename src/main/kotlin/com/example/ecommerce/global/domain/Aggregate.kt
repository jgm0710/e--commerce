package com.example.ecommerce.global.domain

import java.time.Instant

interface Aggregate {
    val id: AggregateId
    val createdAt: Instant
    var lastModifiedAt: Instant

    fun refreshLastModifiedAt() {
        this.lastModifiedAt = Instant.now()
    }
}
