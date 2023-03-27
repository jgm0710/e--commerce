package com.example.ecommerce.adepter.out.event

import org.springframework.data.jpa.repository.JpaRepository

interface OutBoxRepository:JpaRepository<OutBox, Long> {
}
