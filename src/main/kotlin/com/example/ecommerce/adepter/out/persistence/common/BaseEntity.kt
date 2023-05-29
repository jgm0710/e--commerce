package com.example.ecommerce.adepter.out.persistence.common

import java.time.Instant
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity(open val createdAt: Instant, open val lastModifiedAt: Instant)