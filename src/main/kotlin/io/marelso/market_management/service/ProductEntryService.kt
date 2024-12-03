package io.marelso.market_management.service

import io.marelso.market_management.domain.ProductEntry
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.dto.SaleProductDto
import io.marelso.market_management.repository.ProductEntryRepository
import org.springframework.stereotype.Service

@Service
class ProductEntryService(
    private val repository: ProductEntryRepository,
    private val productService: ProductService
) {
    fun create(
        cart: SaleProductDto,
        transactionId: String,
        accountId: String,
        storeId: String
    ): ProductDto {
        productService.existsById(cart.productId)

        for(index in 1..cart.quantity){
            repository.save(
                ProductEntry(
                    transactionId = transactionId,
                    productId = cart.productId,
                    storeId = storeId,
                    accountId = accountId,
                    price = cart.cost,
                )
            )
        }

        return productService.getById(cart.productId)
    }
}