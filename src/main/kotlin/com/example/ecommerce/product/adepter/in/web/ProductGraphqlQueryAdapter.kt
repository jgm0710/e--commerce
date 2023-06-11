package com.example.ecommerce.product.adepter.`in`.web

import com.example.ecommerce.product.adepter.`in`.web.schema.ProductCategorySchema
import com.example.ecommerce.product.application.port.`in`.ProductCategoryQueryCase
import com.example.ecommerce.product.application.port.`in`.ProductCategoryQueryCase.Companion.invoke
import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductCategoryId
import com.example.ecommerce.global.pagination.PageQuery
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ProductGraphqlQueryAdapter(
    val productCategoryQueryCase: ProductCategoryQueryCase
) {

    @QueryMapping
    fun productCategories(
        @Argument id: Long?,
        @Argument name: String?,
        @Argument code: String?,
        @Argument page: Long?,
        @Argument limit: Long?,
    ): List<ProductCategorySchema> = productCategoryQueryCase(
        productCategoryId = id?.let { ProductCategoryId(it) },
        name = name,
        code = code,
        pageQuery = PageQuery(page ?: 1, limit ?: 10)
    ).map { it.toSchema() }
}

private fun ProductCategory.toSchema(): ProductCategorySchema {
    return ProductCategorySchema(
        id = id.value,
        name = name,
        code = code,
        createdAt = createdAt.toString(),
        lastModifiedAt = lastModifiedAt.toString()
    )
}
