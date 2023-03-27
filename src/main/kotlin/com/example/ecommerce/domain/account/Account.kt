package com.example.ecommerce.domain.account

import com.example.ecommerce.global.domain.AbstractAggregate

class Account(
    val accountType: AccountType,
    val loginId: String,
    val password: String?
) : AbstractAggregate<AccountId>()
