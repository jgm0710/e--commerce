package com.example.ecommerce.brand.domain.exception

import com.example.ecommerce.brand.domain.BrandId

class BrandNotFoundException(brandId: BrandId) : Throwable() {

}
