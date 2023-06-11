package com.example.ecommerce.application.port.file.out

import com.example.ecommerce.domain.file.FileStorage

interface SaveFileStoragePort {

    fun save(fileStorage: FileStorage): FileStorage
}
