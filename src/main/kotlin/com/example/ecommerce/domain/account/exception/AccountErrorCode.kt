package com.example.ecommerce.domain.account.exception

import com.example.ecommerce.global.exception.ErrorCode
import org.springframework.http.HttpStatus

enum class AccountErrorCode(override val httpStatus: HttpStatus, override val code: String, override val description: String) : ErrorCode {

    ACCOUNT_NOT_FOUND(httpStatus = HttpStatus.NOT_FOUND, code = "AC001", description = "계정을 찾을 수 없습니다."),
    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "AC002", "Login Id 가 중복됩니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "AC003", "비밀번호가 일치하지 않습니다.")
}
