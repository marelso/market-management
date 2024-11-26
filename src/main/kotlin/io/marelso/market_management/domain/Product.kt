package io.marelso.market_management.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

data class Product(
    val id: String? = null,
    val storeId: String,
    val name: String,
    val price: Double,
    val pictureUrl: String? = null,
    val description: String? = null,
    @CreatedDate val createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate val lastModifiedDate: LocalDateTime = LocalDateTime.now()
)
