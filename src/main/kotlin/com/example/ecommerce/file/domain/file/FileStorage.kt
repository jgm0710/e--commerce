package com.example.ecommerce.file.domain.file

import com.example.ecommerce.global.domain.AbstractAggregate
import java.nio.file.Path
import java.nio.file.Paths

data class FileStorage(
    val fileName: String,
    val uploadPath: String,
    val fileType: String,
    val size: Long,
) : AbstractAggregate<FileStorageId>() {

    val fileStoredPath: Path = Paths.get(uploadPath, fileName)
    val downloadPath : String = "/files/${fileName}"
}