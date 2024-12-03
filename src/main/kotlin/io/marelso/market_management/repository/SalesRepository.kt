package io.marelso.market_management.repository

import io.marelso.market_management.domain.Sale
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SalesRepository: MongoRepository<Sale, String> {
    fun findFirstByProductId(productId: String): Optional<Sale>
    fun countSalesByProductIdAndSellingDateIsNull(productId: String): Int

    @Query("SELECT avg(s.buyingPrice) FROM Sales s WHERE productId = :productId")
    fun avgCostByProductId(productId: String): Double?

    fun findAllByProductId(productId: String, pageable: Pageable): Page<Sale>
}