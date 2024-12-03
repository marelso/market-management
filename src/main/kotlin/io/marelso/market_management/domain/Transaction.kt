package io.marelso.market_management.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

data class Transaction(
    val id: String? = null,
    val storeId: String,
    val accountId: String,
    val type: TransactionType,
    val invoiceUrl: String? = null,
    val total: Double,
    @CreatedDate
    val createdAt: LocalDateTime? = null,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null,
)
