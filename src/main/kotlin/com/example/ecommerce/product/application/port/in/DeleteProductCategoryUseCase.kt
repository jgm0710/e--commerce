package com.example.ecommerce.product.application.port.`in`

import com.example.ecommerce.product.domain.ProductCategoryId

interface DeleteProductCategoryUseCase {

    fun deleteProductCategory(productCategoryId: ProductCategoryId)

    companion object {
        operator fun DeleteProductCategoryUseCase.invoke(productCategoryId: ProductCategoryId) =
            deleteProductCategory(productCategoryId)
    }
}