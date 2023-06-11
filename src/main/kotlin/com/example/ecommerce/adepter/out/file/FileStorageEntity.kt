package com.example.ecommerce.adepter.out.file

import com.example.ecommerce.adepter.out.persistence.common.BaseEntity
import java.time.Instant
import javax.persistence.*


@Entity
@Table(name = "file_storage")
class FileStorageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(length = 1000)
    val fileName: String,
    @Column(length = 1000)
    val uploadPathString: String,
    val fileType: String,
    val size: Long,
    createdAt: Instant,
    lastModifiedAt: Instant,
) : BaseEntity(createdAt = createdAt, lastModifiedAt = lastModifiedAt)