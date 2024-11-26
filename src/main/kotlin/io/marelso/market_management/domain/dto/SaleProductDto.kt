package io.marelso.market_management.domain.dto

data class SaleProductDto(
    val productId: String,
    val quantity: Int,
    val cost: Double
)