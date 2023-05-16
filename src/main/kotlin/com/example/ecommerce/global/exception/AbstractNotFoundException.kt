package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractNotFoundException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
    httpStatus = HttpStatus.NOT_FOUND,
)
