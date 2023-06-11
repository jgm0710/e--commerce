package com.example.ecommerce.user.adepter.out.persistence.mebmer

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {

    fun existsByNickname(nickname: String): Boolean
}

