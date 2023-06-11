package com.example.ecommerce.user.domain.account.exception

import com.example.ecommerce.global.exception.ErrorCode

enum class AccountErrorCode(override val code: String, override val description: String) : ErrorCode {

    ACCOUNT_NOT_FOUND(code = "AC001", description = "계정을 찾을 수 없습니다."),
    DUPLICATE_LOGIN_ID(code = "AC002", description = "Login Id 가 중복됩니다."),
    PASSWORD_MISMATCH(code = "AC003", description = "비밀번호가 일치하지 않습니다.")
}
