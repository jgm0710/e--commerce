package com.example.ecommerce.brand.application

import com.example.ecommerce.brand.application.port.`in`.BrandQueryCase
import com.example.ecommerce.brand.application.port.`in`.CreateBrandUseCase
import com.example.ecommerce.brand.application.port.`in`.DeleteBrandUseCase
import com.example.ecommerce.brand.application.port.`in`.ModifyBrandUseCase
import com.example.ecommerce.brand.application.port.`in`.command.CreateBrandCommand
import com.example.ecommerce.brand.application.port.`in`.command.ModifyBrandCommand
import com.example.ecommerce.brand.application.port.out.BrandQueryPort
import com.example.ecommerce.brand.application.port.out.DeleteBrandPort
import com.example.ecommerce.brand.application.port.out.SaveBrandPort
import com.example.ecommerce.brand.domain.Brand
import com.example.ecommerce.brand.domain.BrandId
import com.example.ecommerce.brand.domain.exception.BrandNotFoundException
import com.example.ecommerce.global.pagination.PageQuery
import com.example.ecommerce.global.transaction.Transaction
import org.springframework.stereotype.Service

@Service
class BrandAplService(
    private val saveBrandPort: SaveBrandPort,
    private val brandQueryPort: BrandQueryPort,
    private val deleteBrandPort: DeleteBrandPort,
    private val transaction: Transaction
) : CreateBrandUseCase, ModifyBrandUseCase, BrandQueryCase, DeleteBrandUseCase {

    override fun createBrand(command: CreateBrandCommand): Brand =
        transaction.write { saveBrandPort.save(command.createBrand()) }.getOrThrow()

    override fun modifyBrand(command: ModifyBrandCommand) {
        val brand: Brand = brandQueryPort.findById(command.brandId) ?: throw BrandNotFoundException(command.brandId)
        brand.modify(
            imageUrl = command.imageUrl,
            name = command.name,
            introduction = command.introduction,
        ).let { transaction.write { saveBrandPort.save(it) } }
    }

    override fun searchBrand(brandId: BrandId?, name: String?, pageQuery: PageQuery): List<Brand> =
        transaction.readOnly { brandQueryPort.findAllBy(brandId, name, pageQuery) }.getOrThrow()

    override fun deleteBrand(brandId: BrandId) = transaction.write { deleteBrandPort.deleteById(brandId) }.getOrThrow()
}