package com.example.ecommerce.global.exception

abstract class AbstractBadRequestException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
)
