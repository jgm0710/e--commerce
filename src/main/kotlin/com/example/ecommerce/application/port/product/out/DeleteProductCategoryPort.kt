package com.example.ecommerce.application.port.product.out

import com.example.ecommerce.domain.product.ProductCategoryId

interface DeleteProductCategoryPort {

    fun deleteById(productCategoryId: ProductCategoryId)
}
