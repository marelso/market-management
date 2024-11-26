package io.marelso.market_management.service

import io.marelso.market_management.domain.Product
import io.marelso.market_management.domain.dto.CreateProductDto
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.factory.ProductFactory
import io.marelso.market_management.repository.ProductRepository
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

    fun getByStoreId(storeId: String, pageable: Pageable): List<ProductDto> {
        return factory.from(repository.findAllByStoreId(storeId, pageable).map { product: Product ->
            Pair(product, salesService.countSalesByProductId(product.id.orEmpty()))
        })
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