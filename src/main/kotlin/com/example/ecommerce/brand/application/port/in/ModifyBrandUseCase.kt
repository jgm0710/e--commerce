package com.example.ecommerce.brand.application.port.`in`

import com.example.ecommerce.brand.application.port.`in`.command.ModifyBrandCommand

interface ModifyBrandUseCase {

    fun modifyBrand(command: ModifyBrandCommand)

    companion object {
        operator fun ModifyBrandUseCase.invoke(command: ModifyBrandCommand) = modifyBrand(command)
    }
}