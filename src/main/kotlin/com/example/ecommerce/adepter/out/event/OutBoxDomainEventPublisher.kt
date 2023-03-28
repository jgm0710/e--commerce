package com.example.ecommerce.adepter.out.event

import com.example.ecommerce.global.domain.Aggregate
import com.example.ecommerce.global.domain.AggregateId
import com.example.ecommerce.global.event.DomainEvent
import com.example.ecommerce.global.event.DomainEventPublisher
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.reflect.KClass

class OutBoxDomainEventPublisher(
    private val outBoxRepository: OutBoxRepository,
    private val objectMapper: ObjectMapper,
) : DomainEventPublisher {

    override fun publish(aggregateType: String, aggregateId: AggregateId, domainEvents: List<DomainEvent>) {
        domainEvents.forEach { domainEvent ->
            outBoxRepository.save(
                OutBox.create(
                    eventType = domainEvent.javaClass.simpleName,
                    entityId = aggregateId.value.toString(),
                    entityType = aggregateType,
                    eventData = objectMapper.writeValueAsString(domainEvent),
                )
            )
        }
    }

    override fun <A : Aggregate> publish(
        aggregateType: KClass<A>,
        aggregateId: AggregateId,
        domainEvents: List<DomainEvent>,
    ) {
        val checkNotNull = checkNotNull(aggregateType.simpleName) { "[aggregateType.simpleName] 는 null 일 수 없습니다." }
        publish(checkNotNull, aggregateId, domainEvents)
    }
}
