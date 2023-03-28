package com.example.ecommerce.global.event

import com.example.ecommerce.global.domain.Aggregate
import com.example.ecommerce.global.domain.AggregateId
import java.util.function.Function
import kotlin.reflect.KClass

abstract class AbstractAggregateDomainEventPublisher<A : Aggregate, E : DomainEvent>(
    private val domainEventPublisher: DomainEventPublisher,
    private val isSupplier: Function<A, AggregateId>,
    private val aggregateType: KClass<A>,
) {

    fun publish(aggregate: A, domainEvent: E) {
        domainEventPublisher.publish(aggregateType, isSupplier.apply(aggregate), listOf(domainEvent))
    }

    fun publish(aggregate: A, domainEvents: List<E>) {
        domainEventPublisher.publish(aggregateType, isSupplier.apply(aggregate), domainEvents)
    }
}
