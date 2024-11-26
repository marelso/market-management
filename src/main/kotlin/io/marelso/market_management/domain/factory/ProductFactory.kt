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

    fun from(products: List<Product>) = products.map { from(it) }

    fun from(product: Product): ProductDto {
        return ProductDto(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            pictureUrl = product.pictureUrl,
            createdAt = product.createdDate.toString(),
            updatedAt = product.lastModifiedDate.toString()
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