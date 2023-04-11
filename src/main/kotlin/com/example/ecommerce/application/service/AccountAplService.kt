package com.example.ecommerce.application.service

import com.example.ecommerce.application.port.account.`in`.SignInUseCase
import com.example.ecommerce.application.port.account.`in`.command.SignInCommand
import com.example.ecommerce.application.port.account.out.FindAccountPort
import com.example.ecommerce.application.port.account.out.AuthenticationPort
import com.example.ecommerce.domain.account.Account
import com.example.ecommerce.domain.account.AuthInfo
import com.example.ecommerce.domain.account.exception.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountAplService(
    private val findAccountPort: FindAccountPort,
    private val authenticationPort: AuthenticationPort,
) : SignInUseCase {

    override fun signIn(command: SignInCommand): AuthInfo {
        val account: Account = findAccountPort.findByLoginId(command.loginId)
            ?: throw AccountNotFoundException(command.loginId)

        authenticationPort.validatePassword(account, command.password)

        return authenticationPort.createAuthInfo(account)
    }
}
