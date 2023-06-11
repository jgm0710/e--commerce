package com.example.ecommerce.domain.file.exception

import com.example.ecommerce.global.exception.HttpStatusException
import org.springframework.http.HttpStatus

class DownloadFailByUnknownReasonException : HttpStatusException(
    HttpStatus.INTERNAL_SERVER_ERROR,
    FileErrorCode.DOWNLOAD_FAIL_BY_UNKNOWN_REASON
)