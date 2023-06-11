package com.example.ecommerce.product.application

import com.example.ecommerce.product.application.port.`in`.CreateProductionCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.DeleteProductCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.ModifyProductCategoryUseCase
import com.example.ecommerce.product.application.port.`in`.ProductCategoryQueryCase
import com.example.ecommerce.product.application.port.`in`.command.CreateProductionCategoryCommand
import com.example.ecommerce.product.application.port.`in`.command.ModifyProductCategoryCommand
import com.example.ecommerce.product.application.port.out.DeleteProductCategoryPort
import com.example.ecommerce.product.application.port.out.ProductCategoryQueryPort
import com.example.ecommerce.product.application.port.out.SaveProductCategoryPort
import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductCategoryId
import com.example.ecommerce.product.domain.exception.DuplicateProductCategoryCodeException
import com.example.ecommerce.product.domain.exception.DuplicateProductCategoryNameException
import com.example.ecommerce.product.domain.exception.ProductCategoryNotFoundException
import com.example.ecommerce.global.pagination.PageQuery
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

    override fun findAll(
        productCategoryId: ProductCategoryId?,
        name: String?,
        code: String?,
        pageQuery: PageQuery
    ): List<ProductCategory> = productCategoryQueryPort.findAllBy(
        productCategoryId,
        name,
        code,
        pageQuery
    )
}

