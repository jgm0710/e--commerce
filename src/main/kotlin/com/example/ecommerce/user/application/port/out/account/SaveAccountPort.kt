package com.example.ecommerce.user.application.port.out.account

import com.example.ecommerce.user.domain.account.Account

interface SaveAccountPort {

    fun save(account: Account) : Account
}
