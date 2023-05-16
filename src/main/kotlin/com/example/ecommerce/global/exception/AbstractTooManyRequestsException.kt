package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractTooManyRequestsException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.TOO_MANY_REQUESTS,
)
