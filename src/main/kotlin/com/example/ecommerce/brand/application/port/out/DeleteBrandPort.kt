package com.example.ecommerce.brand.application.port.out

import com.example.ecommerce.brand.domain.BrandId

interface DeleteBrandPort {

    fun deleteById(brandId: BrandId)
}
