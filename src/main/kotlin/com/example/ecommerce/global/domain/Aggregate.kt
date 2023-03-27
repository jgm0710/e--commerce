package com.example.ecommerce.global.domain

import java.time.Instant

abstract class Aggregate {
    val createdAt: Instant = Instant.now()
    var lastModifiedAt: Instant = Instant.now()

    fun refreshLastModifiedAt() {
        this.lastModifiedAt = Instant.now()
    }
}
