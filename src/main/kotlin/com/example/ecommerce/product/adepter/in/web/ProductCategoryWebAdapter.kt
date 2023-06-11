package com.example.ecommerce.product.adepter.`in`.web

import com.example.ecommerce.adepter.input.web.ProductCategoriesApi
import com.example.ecommerce.adepter.input.web.model.CreateProductCategoryRequest
import com.example.ecommerce.adepter.input.web.model.CreateProductCategoryResponse
import com.example.ecommerce.adepter.input.web.model.ModifyProductCategoryRequest
import com.example.ecommerce.product.application.port.`in`.CreateProductionCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.CreateProductionCategoryUseCase.Companion.invoke
import com.example.ecommerce.product.application.port.`in`.DeleteProductCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.DeleteProductCategoryUseCase.Companion.invoke
import com.example.ecommerce.product.application.port.`in`.ModifyProductCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.ModifyProductCategoryUseCase.Companion.invoke
import com.example.ecommerce.product.application.port.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.product.application.port.`in`.command.ModifyProductCategoryCommand
import com.example.ecommerce.product.domain.ProductCategoryId
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
            .body(CreateProductCategoryResponse(productCategoryId = createdProductCategoryId.value.toBigDecimal()))
    }

    override fun deleteProductCategory(productCategoryId: BigDecimal): ResponseEntity<Unit> {
        deleteProductCategoryUseCase(productCategoryId = ProductCategoryId(productCategoryId.toLong()))
        return ResponseEntity.noContent().build()
    }

    override fun modifyProductCategory(
        productCategoryId: BigDecimal,
        modifyProductCategoryRequest: ModifyProductCategoryRequest
    ): ResponseEntity<Unit> {
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
