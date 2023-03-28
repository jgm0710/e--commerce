package com.example.ecommerce.global.event

class DomainEventEnvelope(
    val eventId: String,
    val eventType: String,
    val aggregateId: String,
    val aggregateType: String,
    val eventData: String,
)
