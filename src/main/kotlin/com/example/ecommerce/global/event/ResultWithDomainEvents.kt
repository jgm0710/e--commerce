package com.example.ecommerce.global.event

import com.example.ecommerce.global.domain.Aggregate
import com.example.ecommerce.global.event.domainevent.DomainEvent

class ResultWithDomainEvents<A : Aggregate, E : DomainEvent>(
        val result: A,
        val domainEvents: List<E>,
) {

    constructor(result: A, domainEvent: E) : this(result, listOf(domainEvent))

    constructor(result: A) : this(result, emptyList())
}
