package com.example.ecommerce.file.application.port.`in`

import org.springframework.core.io.Resource

interface DownloadFileUseCase {

    fun downloadFile(fileName: String) : Resource
}