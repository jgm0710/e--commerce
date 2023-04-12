package com.example.ecommerce.adepter.`in`.web.member

import com.example.ecommerce.adepter.`in`.web.member.request.ModifyMemberRequest
import com.example.ecommerce.adepter.`in`.web.member.request.SignUpRequest
import com.example.ecommerce.adepter.`in`.web.member.response.MemberDetailResponse
import com.example.ecommerce.adepter.`in`.web.member.response.MemberSimpleResponse
import com.example.ecommerce.application.port.member.`in`.FindMembersQueryCase
import com.example.ecommerce.application.port.member.`in`.GetMemberDetailQueryCase
import com.example.ecommerce.application.port.member.`in`.ModifyMemberUseCase
import com.example.ecommerce.application.port.member.`in`.SignUpUseCase
import com.example.ecommerce.domain.member.MemberId
import com.example.ecommerce.domain.member.model.MemberDetail
import com.example.ecommerce.global.pagination.PageRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["members"])
@RestController
class MemberWebAdepter(
    val signUpUseCase: SignUpUseCase,
    val getMemberDetailQueryCase: GetMemberDetailQueryCase,
    val findMembersQueryCase: FindMembersQueryCase,
    val modifyMemberUseCase: ModifyMemberUseCase
) {

    @ApiOperation("Sign up")
    @PostMapping("/members/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid request: SignUpRequest): MemberDetail {
        val memberId: MemberId = signUpUseCase.signUp(request.toCommand())
        return getMemberDetailQueryCase.getMemberDetail(memberId)
    }

    @ApiOperation("Get member detail")
    @GetMapping("/members/{memberId}")
    fun getMemberDetail(@PathVariable memberId: Long): MemberDetailResponse {
        return getMemberDetailQueryCase.getMemberDetail(MemberId(memberId)).let {
            MemberDetailResponse.from(it)
        }
    }

    @ApiOperation("Find member list")
    @GetMapping("/members")
    fun findMembers(
        @ModelAttribute @Valid pageRequest: PageRequest
    ): List<MemberSimpleResponse> {
        return findMembersQueryCase.findAll(pageRequest.toPageQuery()).map { MemberSimpleResponse.from(it) }
    }

    @ApiOperation("Modify member info")
    @PutMapping("members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modifyMember(
        @PathVariable memberId: Long,
        @RequestBody @Valid request: ModifyMemberRequest
    ) {
        modifyMemberUseCase.modifyMember(request.toCommand(memberId))
    }
}
