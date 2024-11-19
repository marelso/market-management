package io.marelso.market_management.domain.dto

data class AccountCreateDto(
    val firstName: String,
    val lastName: String,
    val pictureUrl: String? = null,
    val email: String,
    val password: String
)
