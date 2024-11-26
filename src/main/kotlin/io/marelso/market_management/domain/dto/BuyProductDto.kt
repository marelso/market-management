package io.marelso.market_management.domain.dto

data class BuyProductDto(
    val productId: String,
    val quantity: Int,
    val cost: Double
)
