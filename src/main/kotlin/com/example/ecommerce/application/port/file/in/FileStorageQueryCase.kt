package com.example.ecommerce.application.port.file.`in`

import com.example.ecommerce.domain.file.FileStorage

interface FileStorageQueryCase {

    fun getStoredFile(fileName: String): FileStorage
}
