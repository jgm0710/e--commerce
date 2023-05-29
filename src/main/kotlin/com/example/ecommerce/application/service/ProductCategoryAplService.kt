package com.example.ecommerce.application.service

import com.example.ecommerce.application.port.product.`in`.CreateProductionCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.DeleteProductCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.ModifyProductCategoryUseCase
import com.example.ecommerce.application.port.product.`in`.ProductCategoryQueryCase
import com.example.ecommerce.application.port.product.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.application.port.product.`in`.command.ModifyProductCategoryCommand
import com.example.ecommerce.application.port.product.out.DeleteProductCategoryPort
import com.example.ecommerce.application.port.product.out.ProductCategoryQueryPort
import com.example.ecommerce.application.port.product.out.SaveProductCategoryPort
import com.example.ecommerce.domain.product.ProductCategory
import com.example.ecommerce.domain.product.ProductCategoryId
import com.example.ecommerce.domain.product.exception.DuplicateProductCategoryCodeException
import com.example.ecommerce.domain.product.exception.DuplicateProductCategoryNameException
import com.example.ecommerce.domain.product.exception.ProductCategoryNotFoundException
import com.example.ecommerce.global.transaction.Transaction
import org.springframework.stereotype.Service

@Service
class ProductCategoryAplService(
    val transaction: Transaction,
    val saveProductCategoryPort: SaveProductCategoryPort,
    val deleteProductCategoryPort: DeleteProductCategoryPort,
    val productCategoryQueryPort: ProductCategoryQueryPort,
) : CreateProductionCategoryUseCase, ModifyProductCategoryUseCase,
    DeleteProductCategoryUseCase, ProductCategoryQueryCase {

    override fun createProductionCategory(command: CreateProductionCategoryCommand): ProductCategoryId {
        transaction.readOnly {
            validateDuplicateName(command.name)
            validateDuplicateCode(command.code)
        }

        val productCategory: ProductCategory = command.createProductCategory()

        return transaction.write {
            saveProductCategoryPort.save(productCategory).id
        }.getOrThrow()
    }

    fun validateDuplicateCode(code: String) {
        val isExistsCode: Boolean = productCategoryQueryPort.existsByCode(code)
        if (isExistsCode) throw DuplicateProductCategoryCodeException()
    }

    fun validateDuplicateName(name: String) {
        val isExistsName: Boolean = productCategoryQueryPort.existsByName(name)
        if (isExistsName) throw DuplicateProductCategoryNameException()
    }

    override fun modifyProductCategory(command: ModifyProductCategoryCommand) {
        val productCategory: ProductCategory = transaction.readOnly {
            productCategoryQueryPort.findById(command.productCategoryId) ?: throw ProductCategoryNotFoundException()
        }.getOrThrow()

        val name: String = command.name
        val code: String = command.code

        if (!productCategory.eqName(name)) transaction.readOnly { validateDuplicateName(name) }
        if (!productCategory.eqCode(code)) transaction.readOnly { validateDuplicateCode(code) }

        val modifiedProductCategory: ProductCategory = productCategory.modify(
            name = name,
            code = code,
        )

        transaction.write {
            saveProductCategoryPort.save(modifiedProductCategory)
        }
    }

    override fun deleteProductCategory(productCategoryId: ProductCategoryId) {
        transaction.write {
            deleteProductCategoryPort.deleteById(productCategoryId)
        }
    }

    override fun findAll(): List<ProductCategory> =
        transaction.readOnly { productCategoryQueryPort.findAll() }.getOrThrow()
}

