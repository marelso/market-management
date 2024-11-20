package io.marelso.market_management.domain

data class Store(
    val id: String? = null,
    val pictureUrl: String? = null,
    val name: String? = null,
    val description: String? = null,
    val address: Address? = null,
)