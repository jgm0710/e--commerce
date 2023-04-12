package com.example.ecommerce.global.pagination

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Min

data class PageRequest(
    @field:Min(value = 1, message = "요청 페이지는 1보다 작을 수 없습니다.")
    val page: Long?,
    @field:Range(min = 1, max = 1000, message = "요청 수량은 1~1000 사이입니다.")
    val limit: Long?
) {

    fun toPageQuery(): PageQuery {
        return PageQuery(page ?: 1, limit ?: 10)
    }
}
