package com.example.ecommerce.adepter.`in`.web.account

import com.example.ecommerce.adepter.`in`.web.account.request.SignInRequest
import com.example.ecommerce.adepter.`in`.web.account.response.AuthInfoResponse
import com.example.ecommerce.application.port.account.`in`.SignInUseCase
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["Auth API"])
@RestController
class AuthWebAdepter(
    private val signInUseCase: SignInUseCase
) {

    @ApiOperation("Sign in")
    @PostMapping("auth/sign-in")
    @ResponseStatus(HttpStatus.OK)
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): AuthInfoResponse {
        return signInUseCase.signIn(signInRequest.toCommand())
            .let { AuthInfoResponse.from(it) }
    }
}
