package com.example.ecommerce.adepter.`in`.web.account

import com.example.ecommerce.adepter.`in`.web.account.request.SignInRequest
import com.example.ecommerce.application.port.account.`in`.SignInUseCase
import com.example.ecommerce.domain.account.AuthInfo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(
    private val signInUseCase: SignInUseCase
) {

    @PostMapping("auth/sign-in")
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): AuthInfo {
        return signInUseCase.signIn(signInRequest.toCommand())
    }
}
