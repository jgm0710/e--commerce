package com.example.ecommerce.product.domain.exception

import com.example.ecommerce.global.exception.AbstractConflictException

class DuplicateProductCategoryCodeException :
    AbstractConflictException(ProductErrorCode.DUPLICATE_PRODUCT_CATEGORY_CODE) {

    override val message: String
        get() = "카테고리 코드가 기존 코드와 중복됩니다."
}