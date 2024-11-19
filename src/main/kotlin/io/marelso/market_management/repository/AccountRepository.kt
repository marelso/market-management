package io.marelso.market_management.repository

import io.marelso.market_management.domain.Account
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository: MongoRepository<Account, String> {
    fun findByEmail(email: String): Account?
}