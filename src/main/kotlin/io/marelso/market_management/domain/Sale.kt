package io.marelso.market_management.domain

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

data class Sale(
    val id: String? = null,
    val productId: String,
    val storeId: String,
    val buyingPrice: Double,
    val sellingPrice: Double? = null,
    val sellingDate: LocalDateTime? = null,
    val invoiceUrl: String? = null,
    val receiptUrl: String? = null,
    @CreatedDate val buyingDate: LocalDateTime? = null
)
