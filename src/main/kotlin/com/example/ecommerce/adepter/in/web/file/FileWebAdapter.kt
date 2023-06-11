package com.example.ecommerce.adepter.`in`.web.file

import com.example.ecommerce.adepter.`in`.web.file.response.FileStorageResponse
import com.example.ecommerce.application.port.file.`in`.DownloadFileUseCase
import com.example.ecommerce.application.port.file.`in`.FileStorageQueryCase
import com.example.ecommerce.application.port.file.`in`.UploadFileUseCase
import com.example.ecommerce.application.port.file.`in`.UploadFileUseCase.Companion.invoke
import com.example.ecommerce.domain.file.FileStorage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.DigestUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.concurrent.TimeUnit


@RestController
class FileWebAdapter(
    private val uploadFileUseCase: UploadFileUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val fileStorageQueryCase: FileStorageQueryCase
) {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/files/upload")
    fun uploadFile(
        @RequestPart files: List<MultipartFile>
    ): List<FileStorageResponse> {
        return uploadFileUseCase(files).map { it.toResponse() }
    }

    @GetMapping("/files/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String): ResponseEntity<Resource?>? {
        val resource: Resource = downloadFileUseCase.downloadFile(fileName)
        val storedFile: FileStorage = fileStorageQueryCase.getStoredFile(fileName)
        val parseContentType: MediaType = getParseContentType(storedFile.fileType)
        val contentDisposition: String = getContentDisposition(resource.filename ?: "")
        val lastModified: Instant = storedFile.createdAt
        return getResourceResponseEntity(resource, parseContentType, contentDisposition, lastModified)
    }


    private fun getResourceResponseEntity(
        resource: Resource,
        parseContentType: MediaType,
        contentDisposition: String,
        lastModified: Instant
    ): ResponseEntity<Resource?>? {
        return try {
            val cacheControl: CacheControl = getCacheControl()
            val lastModifiedString: String = lastModified.toString()

            val byteArray = lastModifiedString.toByteArray(StandardCharsets.UTF_8)
            val md5DigestAsHex = DigestUtils.md5DigestAsHex(byteArray)

            ResponseEntity.ok()
                .contentType(parseContentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .cacheControl(cacheControl)
                .eTag(md5DigestAsHex)
                .body<Resource?>(resource)
        } catch (e: Exception) {
            log.error("MD5 Encoding error 발생", e)
            throw IOException("MD5 Encoding error 발생")
        }
    }

    private fun getCacheControl(): CacheControl {
        val fileCacheSecond: Long = 10 * 60 * 60
        return CacheControl.maxAge(fileCacheSecond, TimeUnit.SECONDS)
            .noTransform()
            .mustRevalidate()
    }

    private fun getContentDisposition(resourceFileName: String): String {
        return "attachment; filename=\"$resourceFileName\""
    }

    private fun getParseContentType(fileType: String): MediaType {
        return MediaType.parseMediaType(fileType)
    }

}

private fun FileStorage.toResponse() : FileStorageResponse{
    return FileStorageResponse(
        id =id.value,
        fileName =fileName,
        downloadPath =downloadPath
    )
}
