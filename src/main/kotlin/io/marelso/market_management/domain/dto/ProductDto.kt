package io.marelso.market_management.domain.dto

data class ProductDto(
    val id: String? = null,
    val pictureUrl: String? = null,
    val description: String? = null,
    val count: Int = 0,
    val name: String,
    val price: Double,
    val storeId: String,
    val createdAt: String,
    val updatedAt: String
)