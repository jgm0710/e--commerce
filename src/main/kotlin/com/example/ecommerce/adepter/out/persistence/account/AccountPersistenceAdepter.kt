package com.example.ecommerce.adepter.out.persistence.account

import com.example.ecommerce.adepter.out.persistence.account.AccountEntity.Companion.toEntity
import com.example.ecommerce.application.port.account.out.FindAccountPort
import com.example.ecommerce.application.port.account.out.SaveAccountPort
import com.example.ecommerce.domain.account.Account
import org.springframework.stereotype.Repository

@Repository
class AccountPersistenceAdepter(
    private val accountJpaRepository: AccountJpaRepository
) : SaveAccountPort, FindAccountPort {

    override fun save(account: Account): Account {
        return accountJpaRepository.save(account.toEntity()).toDomain()
    }

    override fun findByLoginId(loginId: String): Account? {
        return accountJpaRepository.findByLoginId(loginId)?.toDomain()
    }

    override fun existsByLoginId(loginId: String): Boolean {
        return accountJpaRepository.existsByLoginId(loginId)
    }
}
