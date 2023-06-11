package com.example.ecommerce.application.port.file.out

import com.example.ecommerce.domain.file.FileStorage

interface DeleteFileStoragePort {

    fun delete(storedFile: FileStorage)
}
