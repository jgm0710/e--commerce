package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractConflictException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.CONFLICT,
)
