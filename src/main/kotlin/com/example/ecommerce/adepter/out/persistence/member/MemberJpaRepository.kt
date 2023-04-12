package com.example.ecommerce.adepter.out.persistence.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {

    fun existsByNickname(nickname: String): Boolean
}

