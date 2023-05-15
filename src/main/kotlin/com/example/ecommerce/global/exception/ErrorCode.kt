package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

interface ErrorCode {
    val httpStatus : HttpStatus
    val code : String
    val description : String
}
