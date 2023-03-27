package com.example.ecommerce.application.port.account.out

import com.example.ecommerce.domain.account.Account

interface SaveAccountPort {

    fun save(account: Account) : Account
}
