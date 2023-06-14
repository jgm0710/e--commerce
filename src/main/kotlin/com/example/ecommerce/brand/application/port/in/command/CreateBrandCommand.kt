package com.example.ecommerce.brand.application.port.`in`.command

import com.example.ecommerce.brand.domain.Brand

data class CreateBrandCommand(
    val imageUrl: String,
    val name: String,
    val introduction: String,
) {
    fun createBrand() : Brand{
        TODO("Not yet implemented")
    }
}