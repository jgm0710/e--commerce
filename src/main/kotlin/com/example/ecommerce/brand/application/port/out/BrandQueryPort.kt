package com.example.ecommerce.brand.application.port.out

import com.example.ecommerce.brand.domain.Brand
import com.example.ecommerce.brand.domain.BrandId
import com.example.ecommerce.global.pagination.PageQuery

interface BrandQueryPort {

    fun findById(brandId: BrandId): Brand?

    fun findAllBy(brandId: BrandId?, name: String?, pageQuery: PageQuery) : List<Brand>
}
