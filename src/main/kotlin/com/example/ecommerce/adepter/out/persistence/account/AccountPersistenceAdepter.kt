package com.example.ecommerce.adepter.out.persistence.account

import com.example.ecommerce.application.port.account.out.SaveAccountPort
import com.example.ecommerce.domain.account.Account
import org.springframework.stereotype.Repository

@Repository
class AccountPersistenceAdepter : SaveAccountPort {

    override fun save(account: Account): Account {
        TODO("Not yet implemented")
    }
}
