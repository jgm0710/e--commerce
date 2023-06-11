package com.example.ecommerce.user.adepter.out.persistence.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {

    fun findByLoginId(loginId : String) : AccountEntity?

    fun existsByLoginId(loginId: String) : Boolean
}
