package com.example.ecommerce.application.port.product.`in`

import com.example.ecommerce.application.port.product.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.domain.product.ProductCategoryId

interface CreateProductionCategoryUseCase {

    fun createProductionCategory(command: CreateProductionCategoryCommand): ProductCategoryId

    companion object {
        operator fun CreateProductionCategoryUseCase.invoke(command: CreateProductionCategoryCommand) =
            createProductionCategory(command)
    }
}