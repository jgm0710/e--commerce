package com.example.ecommerce.brand.adepter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface BrandJpaRepository : JpaRepository<BrandEntity, Long> {

}