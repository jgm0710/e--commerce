package com.example.ecommerce.user.domain.account.exception

import com.example.ecommerce.global.exception.AbstractUnauthorizedException

class PasswordMismatchException : AbstractUnauthorizedException(AccountErrorCode.PASSWORD_MISMATCH) {

    override val message: String = "비밀번호가 일치하지 않습니다."
}
