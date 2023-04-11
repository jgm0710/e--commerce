package com.example.ecommerce.application.port.account.out

import com.example.ecommerce.domain.account.Account

interface FindAccountPort {

    fun findByLoginId(loginId: String) : Account?

    fun existsByLoginId(loginId: String): Boolean
}
