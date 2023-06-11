package com.example.ecommerce.product.application.port.`in`

import com.example.ecommerce.product.application.port.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.product.domain.ProductCategoryId

interface CreateProductionCategoryUseCase {

    fun createProductionCategory(command: CreateProductionCategoryCommand): ProductCategoryId

    companion object {
        operator fun CreateProductionCategoryUseCase.invoke(command: CreateProductionCategoryCommand) =
            createProductionCategory(command)
    }
}