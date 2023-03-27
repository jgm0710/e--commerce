package com.example.ecommerce.domain.account

import com.example.ecommerce.global.domain.Aggregate

class Account(
    val accountType: AccountType,
    val loginId: String,
    val password: String?,
) : Aggregate() {

    lateinit var id: AccountId
}
