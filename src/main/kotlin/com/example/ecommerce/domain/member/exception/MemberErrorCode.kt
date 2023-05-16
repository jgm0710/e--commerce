package com.example.ecommerce.domain.member.exception

import com.example.ecommerce.global.exception.ErrorCode

enum class MemberErrorCode(override val code: String, override val description: String) : ErrorCode {
    MEMBER_NOT_FOUND(code = "MB001", description = "회원을 찾을 수 없습니다."),
    DUPLICATE_NICKNAME(code = "MB002", description = "닉네임이 중복됩니다."),
}