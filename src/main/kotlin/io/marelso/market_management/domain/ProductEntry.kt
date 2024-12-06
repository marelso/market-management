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
    val price: Double,
    val deleted: Boolean = false,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null,
)
