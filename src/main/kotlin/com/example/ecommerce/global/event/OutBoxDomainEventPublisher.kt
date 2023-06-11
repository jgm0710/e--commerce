package com.example.ecommerce.global.event

import com.example.ecommerce.global.domain.Aggregate
import com.example.ecommerce.global.domain.AggregateId
import com.example.ecommerce.global.event.domainevent.DomainEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class OutBoxDomainEventPublisher(
    private val outBoxRepository: OutBoxRepository,
    private val objectMapper: ObjectMapper,
) : DomainEventPublisher {

    override fun publish(aggregateType: String, aggregateId: AggregateId, domainEvents: List<DomainEvent>) {
        domainEvents.forEach { domainEvent ->
            outBoxRepository.save(
                OutBox.create(
                    eventType = checkNotNull(domainEvent::class.qualifiedName) { "[domainEvent::class.qualifiedName] 는 null 일 수 없습니다." },
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
        val typeName = checkNotNull(aggregateType.qualifiedName) { "[aggregateType.qualifiedName] 는 null 일 수 없습니다." }
        publish(aggregateType = typeName, aggregateId = aggregateId, domainEvents = domainEvents)
    }
}
