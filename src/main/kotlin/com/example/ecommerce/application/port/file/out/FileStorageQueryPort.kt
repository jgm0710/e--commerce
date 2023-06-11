package com.example.ecommerce.application.port.file.out

import com.example.ecommerce.domain.file.FileStorage
import com.example.ecommerce.domain.file.FileStorageId

interface FileStorageQueryPort {

    fun findByFileName(fileName: String): FileStorage?
    fun findById(fileStorageId: FileStorageId): FileStorage?
}
