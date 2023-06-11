package com.example.ecommerce.user.domain.account

import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.global.domain.AbstractAggregate

class Account(
    val accountType: AccountType,
    val memberId: MemberId,
    val loginId: String,
    val password: String?
) : AbstractAggregate<AccountId>() {

    val mustHavePassword: Boolean
        get() {
            return this.password!=null
        }
}
