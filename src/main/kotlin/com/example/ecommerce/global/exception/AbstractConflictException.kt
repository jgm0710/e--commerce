package com.example.ecommerce.global.exception

abstract class AbstractConflictException(
    errorCode: ErrorCode,
) : HttpStatusException(
    errorCode = errorCode,
)
