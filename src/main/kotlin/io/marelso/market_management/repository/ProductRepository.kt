package io.marelso.market_management.repository

import io.marelso.market_management.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: MongoRepository<Product, String> {
    fun findAllByStoreId(storeId: String, pageable: Pageable): Page<Product>
}