package io.marelso.market_management.domain.dto

import io.marelso.market_management.domain.Address

data class CreateStoreDto(
    val name:String,
    val description:String,
    val address: Address,
    val pictureUrl:String? = null,
)
