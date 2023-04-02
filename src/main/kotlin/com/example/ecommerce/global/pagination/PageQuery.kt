package com.example.ecommerce.global.pagination

data class PageQuery(
    val page: Long,
    val limit: Long,
) {

    val offset: Long
        get() = (page - 1) * limit
}
