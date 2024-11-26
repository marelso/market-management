package io.marelso.market_management.repository

import io.marelso.market_management.domain.Sale
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesRepository: MongoRepository<Sale, String> {
}