package com.example.ecommerce.brand.application.port.`in`

import com.example.ecommerce.brand.domain.BrandId

interface DeleteBrandUseCase {

    fun deleteBrand(brandId: BrandId)

    companion object {
        operator fun DeleteBrandUseCase.invoke(brandId: BrandId) = deleteBrand(brandId)
    }
}