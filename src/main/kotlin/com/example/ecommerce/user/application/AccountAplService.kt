package com.example.ecommerce.user.application

import com.example.ecommerce.user.application.port.`in`.account.SignInUseCase
import com.example.ecommerce.user.application.port.`in`.account.command.SignInCommand
import com.example.ecommerce.user.application.port.out.account.FindAccountPort
import com.example.ecommerce.user.domain.account.Account
import com.example.ecommerce.user.domain.account.AuthInfo
import com.example.ecommerce.user.domain.account.AuthenticationProvider
import com.example.ecommerce.user.domain.account.exception.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountAplService(
    private val findAccountPort: FindAccountPort,
    private val authenticationProvider: AuthenticationProvider,
) : SignInUseCase {

    override fun signIn(command: SignInCommand): AuthInfo {
        val account: Account = findAccountPort.findByLoginId(command.loginId)
            ?: throw AccountNotFoundException(command.loginId)

        authenticationProvider.validatePassword(account, command.password)

        return authenticationProvider.createAuthInfo(account)
    }
}
