package io.marelso.market_management.service

import io.marelso.market_management.domain.dto.CreateProductDto
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.factory.ProductFactory
import io.marelso.market_management.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository,
    private val factory: ProductFactory,
    private val salesService: SalesService
) {
    fun create(product: CreateProductDto): ProductDto {
        val entity = repository.save(factory.from(product))
        return factory.from(entity, salesService.countSalesByProductId(entity.id.orEmpty()))
    }

    fun getByStoreId(
        storeId: String,
        query: String,
        pageable: Pageable
    ): Page<ProductDto> {
        val products = repository.findAllByStoreIdAndNameContainsOrDescriptionContains(
            storeId = storeId,
            name = query,
            description = query,
            pageable = pageable
        )
        val dto: List<ProductDto> = factory.from(products.content.map {
            Pair(it, salesService.countSalesByProductId(it.id.orEmpty()))
        })

        return PageImpl(
            dto,
            pageable,
            products.totalElements
        )
    }

    fun getById(id: String): ProductDto {
        val entity = findById(id)
        return factory.from(entity, salesService.countSalesByProductId(entity.id.orEmpty()))
    }

    private fun findById(id: String) = repository.findById(id).orElseThrow {
        throw RuntimeException("Product with id $id not found")
    }

    fun update(id: String, update: CreateProductDto): ProductDto {
        val entity = repository.save(factory.from(findById(id), update))
        return factory.from(entity, salesService.countSalesByProductId(entity.id.orEmpty()))
    }
}