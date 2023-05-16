package com.example.ecommerce.global.exception

import org.apache.catalina.connector.ClientAbortException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler() {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(HttpStatusException::class)
    fun handleHttpStatusException(e: HttpStatusException): ResponseEntity<ErrorResponse> {
        log.warn("Http status exception. {}", e.stackTraceToString())

        return ResponseEntity.status(e.httpStatus)
            .body(ErrorResponse(e.errorCode, e.message))
    }

    @ExceptionHandler(ClientAbortException::class)
    fun handleClientAbortException(e: ClientAbortException): ResponseEntity<ErrorResponse> {
        log.warn("Client connection aborted. {}", e.stackTraceToString())

        return ResponseEntity
            .status(HttpStatus.GATEWAY_TIMEOUT)
            .body(ErrorResponse(CommonErrorCode.BROKEN_PIPE, e.message))
    }

    @ExceptionHandler(value = [BindException::class, HttpMessageNotReadableException::class])
    fun handelValidationException(e: Exception): ResponseEntity<ErrorResponse> {
        log.warn("Request not valid. {}", e.stackTraceToString())

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(CommonErrorCode.REQUEST_NOT_VALID, e.message))
    }
}
