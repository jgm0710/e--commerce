package com.example.ecommerce.application.port.file.`in`

import org.springframework.core.io.Resource

interface DownloadFileUseCase {

    fun downloadFile(fileName: String) : Resource
}