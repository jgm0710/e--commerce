package com.example.ecommerce.adepter.out.event

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface OutBoxRepository : JpaRepository<OutBox, Long> {

    fun findAllByIsPublishedOrderByEventIdDesc(isPublished: Boolean, pageable: Pageable): List<OutBox>

    @Transactional
    @Modifying
    @Query("""
        update OutBox  ob set ob.isPublished = true where  ob.eventId in :eventIds
    """)
    fun updateToPublishByEventIds(eventIds: List<Long>)
}
