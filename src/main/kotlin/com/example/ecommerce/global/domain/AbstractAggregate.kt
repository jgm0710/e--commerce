package com.example.ecommerce.global.domain

import java.time.Instant

abstract class AbstractAggregate<ID : AggregateId> : Aggregate {

    override lateinit var id: ID

    override var createdAt: Instant = Instant.now()

    override var lastModifiedAt: Instant = Instant.now()

    val savedId: Long?
        get() = runCatching {
            this.id.value
        }.getOrNull()
}
