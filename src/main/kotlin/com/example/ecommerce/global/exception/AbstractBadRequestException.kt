package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractBadRequestException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.BAD_REQUEST,
)
