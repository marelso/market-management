package io.marelso.market_management.domain.factory

import io.marelso.market_management.domain.Sale
import io.marelso.market_management.domain.dto.SaleProductDto
import org.springframework.stereotype.Component

@Component
class SalesFactory {
    fun from(buying: SaleProductDto, storeId: String): Sale {
        return Sale(
            productId = buying.productId,
            storeId = storeId,
            buyingPrice = buying.cost,
        )
    }
}