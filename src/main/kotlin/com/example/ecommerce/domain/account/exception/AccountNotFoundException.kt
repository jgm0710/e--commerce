package com.example.ecommerce.domain.account.exception

import com.example.ecommerce.global.exception.AbstractNotFoundException

class AccountNotFoundException(loginId : String) : AbstractNotFoundException("Account not found. $loginId")
