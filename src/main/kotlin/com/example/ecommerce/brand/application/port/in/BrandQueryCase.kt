package com.example.ecommerce.brand.application.port.`in`

import com.example.ecommerce.brand.domain.Brand
import com.example.ecommerce.brand.domain.BrandId
import com.example.ecommerce.global.pagination.PageQuery

interface BrandQueryCase {

    fun searchBrand(brandId: BrandId?, name: String?, pageQuery: PageQuery): List<Brand>

    companion object {
        operator fun BrandQueryCase.invoke(brandId: BrandId?, name: String?, pageQuery: PageQuery): List<Brand> =
            searchBrand(
                brandId = brandId,
                name = name,
                pageQuery = pageQuery
            )
    }
}