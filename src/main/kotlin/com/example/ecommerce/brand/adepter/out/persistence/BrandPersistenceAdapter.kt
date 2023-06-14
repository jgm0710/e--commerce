package com.example.ecommerce.brand.adepter.out.persistence

import com.example.ecommerce.brand.adepter.out.persistence.QBrandEntity.brandEntity
import com.example.ecommerce.brand.application.port.out.BrandQueryPort
import com.example.ecommerce.brand.application.port.out.DeleteBrandPort
import com.example.ecommerce.brand.application.port.out.SaveBrandPort
import com.example.ecommerce.brand.domain.Brand
import com.example.ecommerce.brand.domain.BrandId
import com.example.ecommerce.global.pagination.PageQuery
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BrandPersistenceAdapter(
    private val brandJpaRepository: BrandJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : SaveBrandPort, BrandQueryPort, DeleteBrandPort {

    override fun findById(brandId: BrandId): Brand? {
        return brandJpaRepository.findByIdOrNull(brandId.value)?.toDomain()
    }

    override fun findAllBy(brandId: BrandId?, name: String?, pageQuery: PageQuery): List<Brand> {
        return jpaQueryFactory
            .select(brandEntity)
            .from(brandEntity)
            .where(
                eqId(brandId),
                containsName(name),
            )
            .offset(pageQuery.offset)
            .limit(pageQuery.limit)
            .fetch()
            .map { it.toDomain() }
    }

    private fun containsName(name: String?): BooleanExpression? = name?.run { brandEntity.name.contains(this) }

    private fun eqId(brandId: BrandId?): BooleanExpression? = brandId?.run { brandEntity.id.eq(this.value) }

    override fun deleteById(brandId: BrandId) {
        return brandJpaRepository.deleteById(brandId.value)
    }

    override fun save(createBrand: Brand): Brand {
        return brandJpaRepository.save(createBrand.toEntity()).toDomain()
    }
}

private fun Brand.toEntity(): BrandEntity {
    return BrandEntity(
        id = savedId,
        imageUrl = imageUrl,
        name = name,
        introduction = introduction,
        createdAt = createdAt,
        lastModifiedAt = lastModifiedAt
    )
}

private fun BrandEntity.toDomain(): Brand {
    return Brand(
        imageUrl = imageUrl,
        name = name,
        introduction = introduction
    ).also {
        it.id = BrandId(checkNotNull(id))
        it.createdAt = createdAt
        it.lastModifiedAt = lastModifiedAt
    }
}
