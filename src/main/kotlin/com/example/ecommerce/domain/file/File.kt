package com.example.ecommerce.domain.file

import com.example.ecommerce.global.domain.AbstractAggregate

class File(
    val originalFileName: String,
    val savedFileName: String,
    val savedPath: String,
    val size: Int
) : AbstractAggregate<FileId>()