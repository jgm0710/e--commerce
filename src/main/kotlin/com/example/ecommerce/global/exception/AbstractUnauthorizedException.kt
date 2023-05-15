package com.example.ecommerce.global.exception

abstract class AbstractUnauthorizedException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
)
