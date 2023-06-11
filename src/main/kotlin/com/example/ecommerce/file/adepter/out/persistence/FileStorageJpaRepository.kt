package com.example.ecommerce.file.adepter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface FileStorageJpaRepository : JpaRepository<FileStorageEntity, Long> {

    fun findByFileName(fileName: String): FileStorageEntity?
}