package com.example.ecommerce.file.application.port.out

import com.example.ecommerce.file.domain.file.FileStorage
import com.example.ecommerce.file.domain.file.FileStorageId

interface FileStorageQueryPort {

    fun findByFileName(fileName: String): FileStorage?
    fun findById(fileStorageId: FileStorageId): FileStorage?
}
