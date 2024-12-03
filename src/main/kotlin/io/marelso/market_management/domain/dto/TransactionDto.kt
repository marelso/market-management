package io.marelso.market_management.domain.dto

import io.marelso.market_management.domain.TransactionType

data class TransactionDto(
    val id: String,
    val type: TransactionType,
    val products: List<ProductDto>,
    val total: Double,
    val invoiceUrl: String? = null
)
