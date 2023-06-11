package com.example.ecommerce.user.adepter.`in`.web.member

import com.example.ecommerce.user.adepter.`in`.web.member.request.ModifyMemberRequest
import com.example.ecommerce.user.adepter.`in`.web.member.request.SignUpRequest
import com.example.ecommerce.user.adepter.`in`.web.member.response.MemberDetailResponse
import com.example.ecommerce.user.adepter.`in`.web.member.response.MemberSimpleResponse
import com.example.ecommerce.application.port.member.`in`.*
import com.example.ecommerce.user.domain.member.MemberId
import com.example.ecommerce.user.domain.member.model.MemberDetail
import com.example.ecommerce.global.pagination.PageRequest
import com.example.ecommerce.user.application.port.`in`.member.FindMembersQueryCase
import com.example.ecommerce.user.application.port.`in`.member.GetMemberDetailQueryCase
import com.example.ecommerce.user.application.port.`in`.member.ModifyMemberUseCase
import com.example.ecommerce.user.application.port.`in`.member.SignUpUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

//@Api(tags = ["Member API"])
@RestController
class MemberWebAdepter(
    val signUpUseCase: SignUpUseCase,
    val getMemberDetailQueryCase: GetMemberDetailQueryCase,
    val findMembersQueryCase: FindMembersQueryCase,
    val modifyMemberUseCase: ModifyMemberUseCase
) {

//    @ApiOperation("Sign up")
    @PostMapping("/members/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: SignUpRequest): MemberDetail {
        val memberId: MemberId = signUpUseCase(request.toCommand())
        return getMemberDetailQueryCase(memberId)
    }

//    @ApiOperation("Get member detail")
    @GetMapping("/members/{memberId}")
    fun getMemberDetail(@PathVariable memberId: Long): MemberDetailResponse {
        return getMemberDetailQueryCase(MemberId(memberId)).let {
            MemberDetailResponse.from(it)
        }
    }

//    @ApiOperation("Find member list")
    @GetMapping("/members")
    fun findMembers(
        @ModelAttribute @Valid pageRequest: PageRequest
    ): List<MemberSimpleResponse> {
        return findMembersQueryCase(pageRequest.toPageQuery()).map { MemberSimpleResponse.from(it) }
    }

//    @ApiOperation("Modify member info")
    @PutMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modifyMember(
        @PathVariable memberId: Long,
        @RequestBody @Valid request: ModifyMemberRequest
    ) {
        modifyMemberUseCase(request.toCommand(memberId))
    }
}
