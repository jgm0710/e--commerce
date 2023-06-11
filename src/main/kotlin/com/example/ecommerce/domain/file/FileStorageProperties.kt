package com.example.ecommerce.domain.file

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class FileStorageProperties{

    @Value("\${file.storage.properties.upload-path}")
    lateinit var  uploadPath: String
}
