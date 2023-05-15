package com.example.ecommerce.global.exception

abstract class AbstractTooManyRequestsException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
)
