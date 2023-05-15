package com.example.ecommerce.global.exception

data class ErrorResponse(val code: String, val message: String) {

    constructor(errorCode: ErrorCode, message: String) : this(errorCode.code, message)
}
