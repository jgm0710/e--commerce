package com.example.ecommerce.adepter.out.event

import com.example.ecommerce.global.event.DomainEventEnvelope
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
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

    @Scheduled(cron = "0 */5 * * * *", zone = "Asia/Seoul")
    fun publishDomainEvent() {
        val outBoxes = outBoxRepository.findAllByIsPublished(false)

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

        outBoxRepository.updateToPublishByEventIds(outBoxes.mapNotNull { it.eventId?.toString() })
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
