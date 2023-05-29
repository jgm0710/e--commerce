package com.example.ecommerce.domain.product.exception

import com.example.ecommerce.global.exception.ErrorCode

enum class ProductErrorCode(override val code: String, override val description: String) : ErrorCode {

    PRODUCT_CATEGORY_NOT_FOUND("PC001", "상품 카테고리를 찾을 수 없을 경우.")
}