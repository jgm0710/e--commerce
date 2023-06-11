package com.example.ecommerce.file.application.port.out

import com.example.ecommerce.file.domain.file.FileStorage

interface DeleteFileStoragePort {

    fun delete(storedFile: FileStorage)
}
