package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class HttpStatusException(
     val httpStatus: HttpStatus,
     val errorCode : ErrorCode,
) : RuntimeException()
