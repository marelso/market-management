package io.marelso.market_management.repository

import io.marelso.market_management.domain.ProductEntry
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductEntryRepository: MongoRepository<ProductEntry, String> {
    fun findFirstByProductIdAndDeletedFalse(productId: String): Optional<ProductEntry>
}