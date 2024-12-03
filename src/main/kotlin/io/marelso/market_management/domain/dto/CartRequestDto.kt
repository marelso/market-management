package io.marelso.market_management.domain.dto

data class CartRequestDto(
    val storeId: String,
    val products: List<SaleProductDto>,
    val invoiceUrl: String? = null
)
