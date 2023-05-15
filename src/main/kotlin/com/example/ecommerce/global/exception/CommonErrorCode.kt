package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

enum class CommonErrorCode(
    override val httpStatus: HttpStatus,
    override val code: String,
    override val description: String
) : ErrorCode {

    BROKEN_PIPE(HttpStatus.GATEWAY_TIMEOUT, "CM001", "Broken pipe."),
    REQUEST_NOT_VALID(HttpStatus.BAD_REQUEST, "CM002", "Request data not valid.")
}