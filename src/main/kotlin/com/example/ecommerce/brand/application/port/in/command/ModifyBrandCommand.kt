package com.example.ecommerce.brand.application.port.`in`.command

import com.example.ecommerce.brand.domain.BrandId

data class ModifyBrandCommand(
    val brandId: BrandId,
    val imageUrl: String?,
    val name: String?,
    val introduction: String?,
)