package com.example.ecommerce.domain.member.exception

import com.example.ecommerce.global.exception.ErrorCode
import org.springframework.http.HttpStatus

enum class MemberErrorCode(override val httpStatus: HttpStatus, override val code: String, override val description: String) : ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MB001", "회원을 찾을 수 없습니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "MB002", "닉네임이 중복됩니다."),
}