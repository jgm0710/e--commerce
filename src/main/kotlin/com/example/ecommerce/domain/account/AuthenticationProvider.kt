package com.example.ecommerce.domain.account

interface AuthenticationProvider {

    fun encodePassword(password : String) : String

    fun validatePassword(account: Account, password: String)

    fun createAuthInfo(account: Account)  : AuthInfo
}
