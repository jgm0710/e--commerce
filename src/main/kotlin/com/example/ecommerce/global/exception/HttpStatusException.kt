package com.example.ecommerce.global.exception

abstract class HttpStatusException(
     val errorCode : ErrorCode,
) : RuntimeException()
