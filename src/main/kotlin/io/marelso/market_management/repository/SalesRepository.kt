package io.marelso.market_management.repository

import io.marelso.market_management.domain.Sale
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SalesRepository: MongoRepository<Sale, String> {
    fun findFirstByProductId(productId: String): Optional<Sale>
    fun countSalesByProductId(productId: String): Int
}