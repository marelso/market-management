package io.marelso.market_management.service

import io.marelso.market_management.domain.Sale
import io.marelso.market_management.domain.dto.BuyProductDto
import io.marelso.market_management.domain.factory.SalesFactory
import io.marelso.market_management.repository.SalesRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SalesService(
    private val repository: SalesRepository,
    private val factory: SalesFactory,
    @Lazy private val productService: ProductService
) {
    fun buy(buy: BuyProductDto) {
        buy.products.forEach { product ->
            val productEntity = productService.getById(product.productId)

            for (index in 1..product.quantity) {
                repository.save(factory.from(product, productEntity.storeId))
            }
        }
    }

    fun sell(sell: BuyProductDto) {
        sell.products.forEach { product ->
            val entity = getSaleByProductId(product.productId)
            val productEntity = productService.getById(product.productId)

            for (index in 1..product.quantity) {
                repository.save(
                    entity.copy(
                        sellingPrice = productEntity.price,
                        sellingDate = LocalDateTime.now(),
                        receiptUrl = sell.invoiceUrl,
                    )
                )
            }
        }
    }

    fun countSalesByProductId(productId: String): Int = repository.countSalesByProductIdAndSellingDateIsNull(productId)

    fun getAverageCostByProductId(productId: String): Double = repository.avgCostByProductId(productId) ?: 0.0

    private fun getSaleByProductId(id: String): Sale {
        return repository.findFirstByProductId(id).orElseThrow {
            throw RuntimeException("There are no sales for given product id: $id")
        }
    }
}