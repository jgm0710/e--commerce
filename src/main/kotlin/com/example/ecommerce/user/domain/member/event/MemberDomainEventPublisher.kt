package com.example.ecommerce.user.domain.member.event

import com.example.ecommerce.global.event.AbstractAggregateDomainEventPublisher
import com.example.ecommerce.global.event.DomainEventPublisher
import com.example.ecommerce.global.event.domainevent.MemberDomainEvent
import com.example.ecommerce.user.domain.member.Member
import org.springframework.stereotype.Component

@Component
class MemberDomainEventPublisher(
        domainEventPublisher: DomainEventPublisher,
) : AbstractAggregateDomainEventPublisher<Member, MemberDomainEvent>(
        domainEventPublisher = domainEventPublisher,
        isSupplier = Member::id,
        aggregateType = Member::class
)
