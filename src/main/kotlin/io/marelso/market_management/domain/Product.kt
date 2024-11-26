package io.marelso.market_management.domain

data class Product(
    val id: String? = null,
    val storeId: String,
    val name: String,
    val price: Double,
    val pictureUrl: String? = null,
    val description: String? = null
)
