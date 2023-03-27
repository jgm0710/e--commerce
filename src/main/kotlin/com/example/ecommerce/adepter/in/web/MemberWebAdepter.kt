package com.example.ecommerce.adepter.`in`.web

import com.example.ecommerce.adepter.`in`.web.request.SignUpRequest
import com.example.ecommerce.application.port.member.`in`.GetMemberDetailQueryCase
import com.example.ecommerce.application.port.member.`in`.SignUpUseCase
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.model.MemberDetail
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class MemberWebAdepter(
    val signUpUseCase: SignUpUseCase,
    val getMemberDetailQueryCase: GetMemberDetailQueryCase,
) {

    @PostMapping("/members/sign-up")
    fun signup(@RequestBody @Valid signUpRequest: SignUpRequest): MemberDetail {
        val memberId: MemberId = signUpUseCase.signUp(signUpRequest.toCommand())
        return getMemberDetailQueryCase.getMemberDetail(memberId)
    }
}
