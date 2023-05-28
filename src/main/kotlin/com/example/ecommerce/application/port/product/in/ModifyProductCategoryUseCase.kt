package com.example.ecommerce.application.port.product.`in`

import com.example.ecommerce.application.port.product.`in`.command.ModifyProductCategoryCommand

interface ModifyProductCategoryUseCase {

    fun modifyProductCategory(command: ModifyProductCategoryCommand)

    companion object {
        operator fun ModifyProductCategoryUseCase.invoke(command: ModifyProductCategoryCommand) =
            modifyProductCategory(command)
    }
}