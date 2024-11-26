package io.marelso.market_management.domain.dto

data class BuyProductDto(
    val products: List<SaleProductDto>,
    val invoiceUrl: String
)
