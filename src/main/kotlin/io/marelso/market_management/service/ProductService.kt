package io.marelso.market_management.service

import io.marelso.market_management.domain.dto.CreateProductDto
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.factory.ProductFactory
import io.marelso.market_management.repository.ProductRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository,
    private val factory: ProductFactory
) {
    fun create(product: CreateProductDto): ProductDto {
        return factory.from(repository.save(factory.from(product)))
    }

    fun getByStoreId(storeId: String, pageable: Pageable): List<ProductDto> {
        return factory.from(repository.findAllByStoreId(storeId, pageable))
    }

    fun getById(id: String): ProductDto {
        return factory.from(repository.findById(id).orElseThrow {
            throw RuntimeException("Store with id $id not found")
        })
    }
}