package com.example.ecommerce.product.application.port.`in`

import com.example.ecommerce.product.application.port.`in`.command.ModifyProductCategoryCommand

interface ModifyProductCategoryUseCase {

    fun modifyProductCategory(command: ModifyProductCategoryCommand)

    companion object {
        operator fun ModifyProductCategoryUseCase.invoke(command: ModifyProductCategoryCommand) =
            modifyProductCategory(command)
    }
}