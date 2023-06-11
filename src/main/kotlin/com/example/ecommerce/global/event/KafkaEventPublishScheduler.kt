package com.example.ecommerce.global.event

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class KafkaEventPublishScheduler(
    val outBoxRepository: OutBoxRepository,
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedDelay = 100)
    fun publishDomainEvent() {
        val outBoxes = outBoxRepository.findAllByIsPublishedOrderByEventIdDesc(false, Pageable.ofSize(300))

        outBoxes.asSequence().forEach { outBox ->
            val domainEventEnvelope = DomainEventEnvelope(
                eventId = checkNotNull(outBox.eventId) { "[outBox.eventId] 는 null 일 수 없습니다." }.toString(),
                eventType = checkNotNull(outBox.eventType) { "[outBox.eventType] 는 null 일 수 없습니다." },
                aggregateId = checkNotNull(outBox.entityId) { "[outBox.entityId] 는 null 일 수 없습니다." },
                aggregateType = checkNotNull(outBox.entityType) { "[outBox.entityType] 는 null 일 수 없습니다." },
                eventData = checkNotNull(outBox.eventData) { "[outBox.eventData] 는 null 일 수 없습니다." }
            )

            sendMessage(domainEventEnvelope)
        }

        outBoxRepository.updateToPublishByEventIds(outBoxes.mapNotNull { it.eventId })
    }

    fun sendMessage(
        domainEventEnvelope: DomainEventEnvelope,
    ) {
        kafkaTemplate.send(
            /* topic = */ domainEventEnvelope.aggregateType,
            /* key = */ domainEventEnvelope.aggregateId,
            /* data = */ objectMapper.writeValueAsString(domainEventEnvelope)
        ).addCallback(
            {
                log.debug("Domain event publish success. EventId : {}", domainEventEnvelope.eventId)
            },
            { ex ->
                log.warn("Domain Event publish fail... EventId : $domainEventEnvelope.eventId", ex)
                log.info("Retry send event.")
                sendMessage(domainEventEnvelope)
            }
        )
    }
}
