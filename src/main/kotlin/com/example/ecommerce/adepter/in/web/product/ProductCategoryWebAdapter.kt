package com.example.ecommerce.adepter.`in`.web.product

import com.example.ecommerce.adepter.`in`.web.ProductCategoriesApi
import com.example.ecommerce.adepter.`in`.web.model.CreateProductCategoryRequest
import com.example.ecommerce.adepter.`in`.web.model.CreateProductCategoryResponse
import com.example.ecommerce.adepter.`in`.web.model.ModifyProductCategoryRequest
import com.example.ecommerce.application.port.product.`in`.CreateProductionCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.CreateProductionCategoryUseCase.Companion.invoke
import com.example.ecommerce.application.port.product.`in`.DeleteProductCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.DeleteProductCategoryUseCase.Companion.invoke
import com.example.ecommerce.application.port.product.`in`.ModifyProductCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.ModifyProductCategoryUseCase.Companion.invoke
import com.example.ecommerce.application.port.product.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.application.port.product.`in`.command.ModifyProductCategoryCommand
import com.example.ecommerce.domain.product.ProductCategoryId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import java.math.BigDecimal

@Controller
class ProductCategoryWebAdapter(
    private val createProductionCategoryUseCase: CreateProductionCategoryUseCase,
    private val modifyProductCategoryUseCase: ModifyProductCategoryUseCase,
    private val deleteProductCategoryUseCase: DeleteProductCategoryUseCase
) : ProductCategoriesApi {

    override fun createProductCategory(createProductCategoryRequest: CreateProductCategoryRequest): ResponseEntity<CreateProductCategoryResponse> {
        val createdProductCategoryId: ProductCategoryId =
            createProductionCategoryUseCase(createProductCategoryRequest.toCommand())
        return ResponseEntity.status(HttpStatus.OK)
            .body(CreateProductCategoryResponse().productCategoryId(createdProductCategoryId.value.toBigDecimal()))
    }

    override fun deleteProductCategory(productCategoryId: BigDecimal): ResponseEntity<Void> {
        deleteProductCategoryUseCase(productCategoryId = ProductCategoryId(productCategoryId.toLong()))
        return ResponseEntity.noContent().build()
    }

    override fun modifyProductCategory(
        productCategoryId: BigDecimal,
        modifyProductCategoryRequest: ModifyProductCategoryRequest
    ): ResponseEntity<Void> {
        modifyProductCategoryUseCase(modifyProductCategoryRequest.toCommand(productCategoryId))
        return ResponseEntity.noContent().build()
    }
}

private fun ModifyProductCategoryRequest.toCommand(productCategoryId: BigDecimal): ModifyProductCategoryCommand {
    return ModifyProductCategoryCommand(
        productCategoryId = ProductCategoryId(productCategoryId.toLong()),
        name = name,
        code = code
    )
}

private fun CreateProductCategoryRequest.toCommand(): CreateProductionCategoryCommand {
    return CreateProductionCategoryCommand(
        name = name,
        code = code
    )
}
