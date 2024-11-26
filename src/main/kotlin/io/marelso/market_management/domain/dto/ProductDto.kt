package io.marelso.market_management.domain.dto

data class ProductDto(
    val id: String? = null,
    val pictureUrl: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null
)