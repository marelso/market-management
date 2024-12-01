package io.marelso.market_management.domain.dto

data class Transaction(
    val products: List<SaleProductDto>,
    val invoiceUrl: String
)
