package com.example.ecommerce.file.adepter.out.persistence

import com.example.ecommerce.file.application.port.out.DeleteFileStoragePort
import com.example.ecommerce.file.application.port.out.FileStorageQueryPort
import com.example.ecommerce.file.application.port.out.SaveFileStoragePort
import com.example.ecommerce.file.domain.file.FileStorage
import com.example.ecommerce.file.domain.file.FileStorageId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class FileStoragePersistenceAdapter(
    private val fileStorageJpaRepository: FileStorageJpaRepository
) : SaveFileStoragePort, FileStorageQueryPort, DeleteFileStoragePort {

    override fun save(fileStorage: FileStorage): FileStorage {
        return fileStorageJpaRepository.save(fileStorage.toEntity()).toDomain()
    }

    override fun findByFileName(fileName: String): FileStorage? {
        return fileStorageJpaRepository.findByFileName(fileName)?.toDomain()
    }

    override fun findById(fileStorageId: FileStorageId): FileStorage? {
        return fileStorageJpaRepository.findByIdOrNull(fileStorageId.value)?.toDomain()
    }

    override fun delete(storedFile: FileStorage) {
        return fileStorageJpaRepository.deleteById(storedFile.id.value)
    }

    private fun FileStorageEntity.toDomain(): FileStorage {
        return FileStorage(
            fileName = fileName,
            uploadPath = uploadPathString,
            fileType = fileType,
            size = size
        ).also {
            it.id = FileStorageId(checkNotNull(this.id))
            it.createdAt = createdAt
            it.lastModifiedAt = lastModifiedAt
        }
    }

    private fun FileStorage.toEntity(): FileStorageEntity {
        return FileStorageEntity(
            id = savedId,
            fileName = fileName,
            uploadPathString = uploadPath,
            fileType = fileType,
            size = size,
            createdAt = createdAt,
            lastModifiedAt = lastModifiedAt,
        )
    }
}