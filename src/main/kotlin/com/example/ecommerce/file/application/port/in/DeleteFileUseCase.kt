package com.example.ecommerce.file.application.port.`in`

import com.example.ecommerce.file.domain.file.FileStorageId

interface DeleteFileUseCase {

    fun deleteFile(fileStorageId: FileStorageId)

    fun deleteFile(fileName: String)
}