package com.example.ecommerce.application.port.file.`in`

import com.example.ecommerce.domain.file.FileStorageId

interface DeleteFileUseCase {

    fun deleteFile(fileStorageId: FileStorageId)

    fun deleteFile(fileName: String)
}