package com.example.ecommerce.file.application.port.`in`

import com.example.ecommerce.file.domain.file.FileStorage

interface FileStorageQueryCase {

    fun getStoredFile(fileName: String): FileStorage
}
