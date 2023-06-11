package com.example.ecommerce.product.domain.exception

import com.example.ecommerce.global.exception.AbstractConflictException

class DuplicateProductCategoryNameException :
    AbstractConflictException(ProductErrorCode.DUPLICATE_PRODUCT_CATEGORY_NAME) {

    override val message: String
        get() = "카테고리 이름이 기존 카테고리 이름과 중복됩니다."
}