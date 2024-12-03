package io.marelso.market_management.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

data class Transaction(
    val id: String? = null,
    val productId: String,
    val storeId: String,
    val accountId: String,
    val type: TransactionType,
    val value: Double,
    val invoiceUrl: String,
    @CreatedDate
    val createdAt: LocalDateTime,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime,
)
