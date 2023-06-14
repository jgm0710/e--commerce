package com.example.ecommerce.brand.application.port.out

import com.example.ecommerce.brand.domain.Brand

interface SaveBrandPort {

    fun save(createBrand: Brand): Brand
}
