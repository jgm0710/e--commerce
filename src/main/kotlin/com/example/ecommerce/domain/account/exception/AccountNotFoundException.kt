package com.example.ecommerce.domain.account.exception

import com.example.ecommerce.global.exception.AbstractNotFoundException

class AccountNotFoundException(loginId: String) : AbstractNotFoundException(AccountErrorCode.ACCOUNT_NOT_FOUND) {

    override val message: String = "Account not found. LoginId : [$loginId]"
}
