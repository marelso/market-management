package io.marelso.market_management.service

import io.marelso.market_management.domain.ProductEntry
import io.marelso.market_management.domain.Transaction
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.dto.SaleProductDto
import io.marelso.market_management.repository.ProductEntryRepository
import io.marelso.market_management.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductEntryService(
    private val repository: ProductEntryRepository,
    private val productService: ProductService
) {
    fun create(
        saleProductDto: SaleProductDto,
        transactionId: String,
        accountId: String,
        storeId: String
    ): ProductDto {
        if(productService.existsById(saleProductDto.productId)) {
            repository.save(
                ProductEntry(
                    transactionId = transactionId,
                    productId = saleProductDto.productId,
                    storeId = storeId,
                    accountId = accountId,
                    cost = saleProductDto.cost,
                )
            )

            return productService.getById(saleProductDto.productId)
        } else {
            throw RuntimeException("Product with id ${saleProductDto.productId} not found")
        }
    }
}