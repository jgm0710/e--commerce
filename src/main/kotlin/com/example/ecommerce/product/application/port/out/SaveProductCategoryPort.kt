package com.example.ecommerce.product.application.port.out

import com.example.ecommerce.product.domain.ProductCategory

interface SaveProductCategoryPort {

    fun save(productCategory: ProductCategory): ProductCategory
}
