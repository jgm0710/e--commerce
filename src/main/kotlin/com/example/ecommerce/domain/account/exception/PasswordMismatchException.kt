package com.example.ecommerce.domain.account.exception

import com.example.ecommerce.global.exception.AbstractUnauthorizedException

class PasswordMismatchException: AbstractUnauthorizedException("비밀번호가 일치하지 않습니다.")
