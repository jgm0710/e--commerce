package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractForbiddenException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.FORBIDDEN,
)
