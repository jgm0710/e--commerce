package com.example.ecommerce.global.exception

import org.apache.catalina.connector.ClientAbortException
import org.slf4j.LoggerFactory
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
        val errorCode = e.errorCode
        log.warn("Http status exception. {}", e.stackTraceToString())
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode, e.message ?: errorCode.description))
    }

    @ExceptionHandler(ClientAbortException::class)
    fun handleClientAbortException(e: ClientAbortException): ResponseEntity<ErrorResponse> {

        log.warn("Client connection aborted. {}", e.stackTraceToString())
        val errorCode = CommonErrorCode.BROKEN_PIPE
        val errorResponse = ErrorResponse(errorCode, e.message ?: errorCode.description)
        return ResponseEntity.status(errorCode.httpStatus).body(errorResponse)
    }

    @ExceptionHandler(value = [BindException::class, HttpMessageNotReadableException::class])
    fun handelValidationException(e: Exception): ResponseEntity<ErrorResponse> {
        log.warn("Request not valid. {}", e.stackTraceToString())
        val errorCode = CommonErrorCode.REQUEST_NOT_VALID
        val errorResponse = ErrorResponse(errorCode, e.message ?: errorCode.description)
        return ResponseEntity.status(errorCode.httpStatus).body(errorResponse)
    }
}
