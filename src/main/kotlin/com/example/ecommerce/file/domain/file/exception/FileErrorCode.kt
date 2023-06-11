package com.example.ecommerce.file.domain.file.exception

import com.example.ecommerce.global.exception.ErrorCode

enum class FileErrorCode(override val code: String, override val description: String) : ErrorCode {

    UPLOAD_FAIL_BY_UNKNOWN_REASON("FS001", "알 수 없는 이유로 파일 업로드에 실패했습니다."),
    DOWNLOAD_FAIL_BY_UNKNOWN_REASON("FS002", "알 수 없는 이유로 파일 다운로드에 실패했습니다."),
    DELETE_FAIL_BY_UNKNOWN_REASON("FS003", "알 수 없는 이유로 파일 삭제에 실패했습니다."),
    STORED_FILE_NOT_FOUND("FS004", "저장된 파일을 찾을 수 없습니다."),
    NOT_VALID_FILE("FS005","파일 형식이 올바르지 않습니다."),
}