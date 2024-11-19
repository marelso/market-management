package io.marelso.market_management.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class TokenProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)