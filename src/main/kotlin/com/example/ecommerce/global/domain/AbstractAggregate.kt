package com.example.ecommerce.global.domain

import java.time.Instant

abstract class AbstractAggregate<ID : AggregateId> : Aggregate {

    override lateinit var id: ID

    override val createdAt: Instant = Instant.now()

    override var lastModifiedAt: Instant = Instant.now()
}
