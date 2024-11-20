package io.marelso.market_management.repository

import io.marelso.market_management.domain.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository: MongoRepository<Store, String> {
}