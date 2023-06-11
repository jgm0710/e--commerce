package com.example.ecommerce.file.application

import com.example.ecommerce.file.application.port.`in`.DeleteFileUseCase
import com.example.ecommerce.file.application.port.`in`.DownloadFileUseCase
import com.example.ecommerce.file.application.port.`in`.FileStorageQueryCase
import com.example.ecommerce.file.application.port.`in`.UploadFileUseCase
import com.example.ecommerce.file.application.port.out.DeleteFileStoragePort
import com.example.ecommerce.file.application.port.out.FileStorageQueryPort
import com.example.ecommerce.file.application.port.out.SaveFileStoragePort
import com.example.ecommerce.file.domain.file.FileStorage
import com.example.ecommerce.file.domain.file.FileStorageId
import com.example.ecommerce.file.domain.file.FileStorageProperties
import com.example.ecommerce.file.domain.file.exception.*
import com.example.ecommerce.global.transaction.Transaction
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.LocalDate
import java.util.*


@Service
class FileAplService(
    private val fileStorageQueryPort: FileStorageQueryPort,
    private val saveFileStoragePort: SaveFileStoragePort,
    private val deleteFileStoragePort: DeleteFileStoragePort,
    fileStorageProperties: FileStorageProperties,
    private val transaction: Transaction,
) : UploadFileUseCase, DownloadFileUseCase, DeleteFileUseCase, FileStorageQueryCase {

    private val log = LoggerFactory.getLogger(this::class.java)

    val fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadPath).toAbsolutePath().normalize()

    companion object {
        private val DENIED_FILE_EXTENSION = arrayOf(".exe", ".sh", ".zip", ".alz")
    }

    override fun uploadFile(file: MultipartFile): FileStorage {
        val originalFilename = getOriginalFileName(file)
        log.info("upload file. original file name : {}", originalFilename)
        fileExtensionCheck(originalFilename)
        val fileStoredName: String = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(originalFilename)
        val finalFileStorageLocation: Path = createTodayDir(fileStorageLocation)

        return transaction.write {
            if (fileStoredName.contains("..")) {
                throw FileNotFoundException("File name contains \"..\". File name : $fileStoredName")
            }

            storeFile(file, fileStoredName, finalFileStorageLocation)

            val fileStorage = FileStorage(
                fileName = fileStoredName,
                uploadPath = finalFileStorageLocation.toAbsolutePath().toString(),
                fileType = file.contentType ?: "",
                size = file.size
            )

            return@write saveFileStoragePort.save(fileStorage)
        }.getOrElse {
            log.warn("Could not store file.", it)
            throw UploadFailByUnknownReasonException()
        }
    }

    override fun uploadFiles(files: List<MultipartFile>): List<FileStorage> {
        return files.map { uploadFile(it) }
    }

    @Throws(IOException::class)
    private fun storeFile(file: MultipartFile, fileStoredName: String, finalFileStorageLocation: Path) {
        val targetLocation: Path = finalFileStorageLocation.resolve(fileStoredName)
        Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
    }

    override fun downloadFile(fileName: String): Resource {
        val storedFile: FileStorage = transaction.readOnly { getStoredFile(fileName) }.getOrThrow()
        return getResource(storedFile.fileName, storedFile.uploadPath)
    }

    override fun deleteFile(fileName: String) {
        val storedFile: FileStorage = getStoredFile(fileName)
        deleteFileStoragePort.delete(storedFile)
        deleteActualFile(storedFile)
    }

    override fun deleteFile(fileStorageId: FileStorageId) {
        val storedFile: FileStorage = getStoredFile(fileStorageId)
        transaction.write {
            deleteFileStoragePort.delete(storedFile)
            deleteActualFile(storedFile)
        }
    }

    fun deleteActualFile(storedFile: FileStorage) {
        try {
            val fileStoredPath: Path = storedFile.fileStoredPath
            Files.deleteIfExists(fileStoredPath)
        } catch (e: IOException) {
            log.warn("File delete fail for unknown reason", e)
            throw DeleteFailByUnknownReasonException()
        }
    }

    override fun getStoredFile(fileName: String): FileStorage =
        fileStorageQueryPort.findByFileName(fileName) ?: throw StoredFileNotFoundException(fileName)

    fun getStoredFile(fileStorageId: FileStorageId): FileStorage =
        fileStorageQueryPort.findById(fileStorageId) ?: throw StoredFileNotFoundException(fileStorageId)

    fun getResource(fileName: String, uploadPath: String): Resource {
        val path: Path = Path.of(uploadPath)
        val fileStoredPath: Path = path.resolve(fileName).normalize()
        return try {
            val urlResource = UrlResource(fileStoredPath.toUri())
            if (urlResource.exists()) {
                urlResource
            } else {
                log.warn(
                    "The requested file does not actually exist, File name : {}, Upload path : {}",
                    fileName,
                    uploadPath
                )
                throw DownloadFailByUnknownReasonException()
            }
        } catch (e: MalformedURLException) {
            log.warn("An unknown warn occurred while converting the file to a resource", e)
            throw DownloadFailByUnknownReasonException()
        }
    }

    private fun createTodayDir(fileStorageLocation: Path): Path {
        val now = LocalDate.now()
        val year = now.year.toString()
        val month = now.monthValue.toString()
        val dayOfMonth = now.dayOfMonth.toString()
        val datePath: Path = Paths.get(year, month, dayOfMonth)
        val finalFileStorageLocation: Path = fileStorageLocation.resolve(datePath)
        try {
            if (Files.notExists(finalFileStorageLocation)) {
                Files.createDirectories(finalFileStorageLocation)
            }
        } catch (e: Exception) {
            log.error("Could not create today directory", e)
            throw UploadFailByUnknownReasonException()
        }
        return finalFileStorageLocation
    }

    private fun getOriginalFileName(file: MultipartFile): String {
        val originalFilename = file.originalFilename
        if (originalFilename.isNullOrBlank()) {
            throw NotValidFileException("Original file name is blank")
        }
        return originalFilename
    }

    private fun fileExtensionCheck(originalFilename: String) {
        val lowFileName = originalFilename.lowercase(Locale.getDefault())
        for (dfe in DENIED_FILE_EXTENSION) {
            if (lowFileName.endsWith(dfe)) {
                throw NotValidFileException("File extension is denied extension. Original file name : $originalFilename")
            }
        }
    }
}