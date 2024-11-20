package io.marelso.market_management.domain.dto

import io.marelso.market_management.domain.StorePermission

data class AccountDto(
    val reference: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val pictureUrl: String? = null,
    val context: List<StorePermission>,
    val jwt: String? = null,
)
