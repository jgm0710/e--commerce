package com.example.ecommerce.domain.file.exception

import com.example.ecommerce.global.exception.HttpStatusException
import org.springframework.http.HttpStatus

class NotValidFileException(override val message: String):HttpStatusException(HttpStatus.BAD_REQUEST, FileErrorCode.NOT_VALID_FILE)