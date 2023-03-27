package com.example.ecommerce.global.exception

import org.springframework.http.HttpStatus

abstract class AbstractNotFoundException(override val message: String?) :
    HttpStatusException(httpStatus = HttpStatus.NOT_FOUND, message = message ?: "Resource Not Found.")
