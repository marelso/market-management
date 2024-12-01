package io.marelso.market_management.domain.factory

import io.marelso.market_management.domain.Product
import io.marelso.market_management.domain.dto.CreateProductDto
import io.marelso.market_management.domain.dto.ProductDto
import org.springframework.stereotype.Component

@Component
class ProductFactory {
    fun from(create: CreateProductDto): Product {
        return Product(
            name = create.name,
            storeId = create.storeId,
            description = create.description,
            price = create.price,
            pictureUrl = create.pictureUrl
        )
    }

    fun from(products: List<Pair<Product, Pair<Int, Double>>>) = products.map { from(it.first, it.second.first, it.second.second) }

    fun from(product: Product, count: Int, average: Double): ProductDto {
        return ProductDto(
            id = product.id,
            storeId = product.storeId,
            name = product.name,
            description = product.description,
            price = product.price,
            pictureUrl = product.pictureUrl,
            createdAt = product.createdDate.toString(),
            updatedAt = product.lastModifiedDate.toString(),
            averageCost = average
        )
    }

    fun from(product: Product, update: CreateProductDto): Product {
        return product.copy(
            name = update.name,
            price = update.price,
            pictureUrl = update.pictureUrl,
            description = update.description,
        )
    }
}