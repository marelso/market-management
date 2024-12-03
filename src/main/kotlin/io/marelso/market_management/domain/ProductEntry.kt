package io.marelso.market_management.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

data class ProductEntry(
    val id: String? = null,
    val transactionId: String,
    val productId: String,
    val storeId: String,
    val accountId: String,
    val cost: Double,
    @CreatedDate
    val createdAt: LocalDateTime,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime,
)
