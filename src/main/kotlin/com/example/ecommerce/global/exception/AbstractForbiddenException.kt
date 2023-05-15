package com.example.ecommerce.global.exception

abstract class AbstractForbiddenException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
)
