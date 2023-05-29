package com.example.ecommerce.application.port.product.out

import com.example.ecommerce.domain.product.ProductCategory

interface SaveProductCategoryPort {

    fun save(productCategory: ProductCategory): ProductCategory
}
