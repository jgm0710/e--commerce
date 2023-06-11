package com.example.ecommerce.product.domain.exception

import com.example.ecommerce.global.exception.AbstractNotFoundException

class ProductCategoryNotFoundException : AbstractNotFoundException(ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND) {

    override val message: String
        get() = "상품 카테고리를 찾을 수 없습니다."
}