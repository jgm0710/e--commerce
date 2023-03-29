package com.example.ecommerce.global.event

import com.example.ecommerce.global.domain.Aggregate
import com.example.ecommerce.global.domain.AggregateId
import com.example.ecommerce.global.event.domainevent.DomainEvent
import kotlin.reflect.KClass

interface DomainEventPublisher {

    fun publish(aggregateType: String, aggregateId: AggregateId, domainEvents: List<DomainEvent>)

    fun <A : Aggregate> publish(aggregateType: KClass<A>, aggregateId: AggregateId, domainEvents: List<DomainEvent>)
}
