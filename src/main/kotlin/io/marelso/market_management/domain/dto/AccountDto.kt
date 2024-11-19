package io.marelso.market_management.domain.dto

data class AccountDto(
    val reference: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val pictureUrl: String? = null,
    val jwt: String? = null,
)
