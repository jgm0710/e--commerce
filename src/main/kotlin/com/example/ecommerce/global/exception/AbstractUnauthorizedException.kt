package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractUnauthorizedException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.UNAUTHORIZED,
)
