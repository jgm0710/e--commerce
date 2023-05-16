package com.example.ecommerce.global.exception

enum class CommonErrorCode(
    override val code: String,
    override val description: String
) : ErrorCode {

    BROKEN_PIPE("CM001", "Broken pipe."),
    REQUEST_NOT_VALID("CM002", "Request data not valid.")
}