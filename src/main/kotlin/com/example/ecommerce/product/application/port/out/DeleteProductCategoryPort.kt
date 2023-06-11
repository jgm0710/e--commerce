package com.example.ecommerce.product.application.port.out

import com.example.ecommerce.product.domain.ProductCategoryId

interface DeleteProductCategoryPort {

    fun deleteById(productCategoryId: ProductCategoryId)
}
