package com.example.ecommerce.adepter.out.event

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.Instant
import javax.persistence.*

@Entity
open class OutBox {
    fun publish() {
        this.isPublished=true
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id", nullable = false)
    open var eventId: String? = null

    @Column(name = "event_type")
    open var eventType: String? = null

    @Column(name = "entity_id")
    open var entityId: String? = null

    @Column(name = "entity_type")
    open var entityType: String? = null

    @Lob
    @Column(name = "event_data")
    open var eventData: String? = null

    @Column(name = "created_at")
    open var createdAt: Instant? = null

    @Column(name = "is_published")
    open var isPublished: Boolean? = null

    constructor(
        eventId: String?,
        eventType: String?,
        entityId: String?,
        entityType: String?,
        eventData: String?,
        createdAt: Instant?,
        isPublished: Boolean?,
    ) {
        this.eventId = eventId
        this.eventType = eventType
        this.entityId = entityId
        this.entityType = entityType
        this.eventData = eventData
        this.createdAt = createdAt
        this.isPublished = isPublished
    }

    companion object {
        fun create(eventType: String, entityId: String, entityType: String, eventData: String): OutBox {
            return OutBox(
                eventId = null,
                eventType = eventType,
                entityId = entityId,
                entityType = entityType,
                eventData = eventData,
                createdAt = Instant.now(),
                isPublished = false
            )
        }
    }
}
