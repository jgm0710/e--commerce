package com.example.ecommerce.adepter.`in`.web.member

import com.example.ecommerce.adepter.`in`.web.member.request.ModifyMemberRequest
import com.example.ecommerce.adepter.`in`.web.member.request.SignUpRequest
import com.example.ecommerce.application.port.member.`in`.FindMembersQueryCase
import com.example.ecommerce.application.port.member.`in`.GetMemberDetailQueryCase
import com.example.ecommerce.application.port.member.`in`.ModifyMemberUseCase
import com.example.ecommerce.application.port.member.`in`.SignUpUseCase
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.model.MemberDetail
import com.example.ecommerce.domain.member.model.MemberSimple
import com.example.ecommerce.global.pagination.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MemberWebAdepter(
    val signUpUseCase: SignUpUseCase,
    val getMemberDetailQueryCase: GetMemberDetailQueryCase,
    val findMembersQueryCase: FindMembersQueryCase,
    val modifyMemberUseCase: ModifyMemberUseCase
) {

    @PostMapping("/members/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody @Valid request: SignUpRequest): MemberDetail {
        val memberId: MemberId = signUpUseCase.signUp(request.toCommand())
        return getMemberDetailQueryCase.getMemberDetail(memberId)
    }

    @GetMapping("/members/{memberId}")
    fun getMemberDetail(@PathVariable memberId: Long): MemberDetail {
        return getMemberDetailQueryCase.getMemberDetail(MemberId(memberId))
    }

    @GetMapping("/members")
    fun getMemberDetail(
        @ModelAttribute @Valid pageRequest: PageRequest
    ): List<MemberSimple> {
        return findMembersQueryCase.findAll(pageRequest.pageQuery)
    }

    @PutMapping("members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modifyMember(
        @PathVariable memberId: Long,
        @RequestBody @Valid request: ModifyMemberRequest
    ) {
        modifyMemberUseCase.modifyMember(request.toCommand(memberId))
    }
}
