package com.example.ecommerce.adepter.out.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileStorageJpaRepository : JpaRepository<FileStorageEntity, Long> {

    fun findByFileName(fileName: String): FileStorageEntity?
}