package com.example.ecommerce.application.port.file.`in`

import com.example.ecommerce.domain.file.FileStorage
import org.springframework.web.multipart.MultipartFile

interface UploadFileUseCase {

    fun uploadFile(file: MultipartFile): FileStorage

    fun uploadFiles(files: List<MultipartFile>): List<FileStorage>

    companion object {

        operator fun UploadFileUseCase.invoke(file: MultipartFile) = uploadFile(file)

        operator fun UploadFileUseCase.invoke(files: List<MultipartFile>) = uploadFiles(files)
    }
}