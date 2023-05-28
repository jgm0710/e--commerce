package com.example.ecommerce.application.port.product

import com.example.ecommerce.domain.product.ProductCategoryId

interface DeleteProductCategoryUseCase {

    fun deleteProductCategory(productCategoryId: ProductCategoryId)

    companion object {
        operator fun DeleteProductCategoryUseCase.invoke(productCategoryId: ProductCategoryId) =
            deleteProductCategory(productCategoryId)
    }
}