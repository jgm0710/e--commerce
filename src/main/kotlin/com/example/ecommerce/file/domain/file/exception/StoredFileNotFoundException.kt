package com.example.ecommerce.file.domain.file.exception

import com.example.ecommerce.file.domain.file.FileStorageId
import com.example.ecommerce.global.exception.HttpStatusException
import org.springframework.http.HttpStatus

class StoredFileNotFoundException() : HttpStatusException(
    httpStatus = HttpStatus.NOT_FOUND,
    errorCode = FileErrorCode.STORED_FILE_NOT_FOUND
) {

    override var message: String? = super.message

    constructor(fileName: String) : this() {
        this.message = "파일명으로 파일을 찾을 수 없습니다. Filename : $fileName"
    }

    constructor(fileStorageId: FileStorageId) : this(){
        this.message = "파일 식별자로 파일을 찾을 수 없습니다. FileId : $fileStorageId"
    }
}