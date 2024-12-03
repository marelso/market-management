package io.marelso.market_management.repository

import io.marelso.market_management.domain.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: MongoRepository<Transaction, String> {
}