package com.example.ecommerce.user.application.port.out.account

import com.example.ecommerce.user.domain.account.Account

interface FindAccountPort {

    fun findByLoginId(loginId: String) : Account?

    fun existsByLoginId(loginId: String): Boolean
}
