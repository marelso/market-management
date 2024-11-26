package io.marelso.market_management.domain.dto

data class CreateProductDto(
    val storeId: String,
    val name: String,
    val price: Double,
    val pictureUrl: String? = null,
    val description: String? = null
)
