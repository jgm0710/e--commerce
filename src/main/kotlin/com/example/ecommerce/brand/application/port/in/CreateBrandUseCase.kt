package com.example.ecommerce.brand.application.port.`in`

import com.example.ecommerce.brand.application.port.`in`.command.CreateBrandCommand
import com.example.ecommerce.brand.domain.Brand

interface CreateBrandUseCase {

    fun createBrand(command: CreateBrandCommand) : Brand

    companion object {
        operator fun CreateBrandUseCase.invoke(command: CreateBrandCommand) = createBrand(command)
    }
}