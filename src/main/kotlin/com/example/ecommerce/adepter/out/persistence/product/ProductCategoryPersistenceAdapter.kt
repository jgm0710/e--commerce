package com.example.ecommerce.adepter.out.persistence.product

import com.example.ecommerce.adepter.out.persistence.product.ProductCategoryEntity.Companion.toEntity
import com.example.ecommerce.application.port.product.out.DeleteProductCategoryPort
import com.example.ecommerce.application.port.product.out.ProductCategoryQueryPort
import com.example.ecommerce.application.port.product.out.SaveProductCategoryPort
import com.example.ecommerce.domain.product.ProductCategory
import com.example.ecommerce.domain.product.ProductCategoryId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ProductCategoryPersistenceAdapter(
    private val productCategoryJpaRepository: ProductCategoryJpaRepository
) : SaveProductCategoryPort, DeleteProductCategoryPort, ProductCategoryQueryPort {

    override fun save(productCategory: ProductCategory): ProductCategory {
        return productCategoryJpaRepository.save(productCategory.toEntity()).toDomain()
    }

    override fun deleteById(productCategoryId: ProductCategoryId) {
        productCategoryJpaRepository.deleteById(productCategoryId.value)
    }

    override fun findById(productCategoryId: ProductCategoryId): ProductCategory? {
        return productCategoryJpaRepository.findByIdOrNull(productCategoryId.value)?.toDomain()
    }

    override fun findAll(): List<ProductCategory> {
        return productCategoryJpaRepository.findAll().map { it.toDomain() }
    }
}