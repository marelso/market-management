package io.marelso.market_management.service

import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.Store
import io.marelso.market_management.domain.dto.CreateStoreDto
import io.marelso.market_management.repository.StoreRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val repository: StoreRepository,
    @Lazy private var accountService: AccountService
) {
    fun create(account: Account, store: CreateStoreDto): Store {
        val entity = repository.save(
            Store(
                name = store.name,
                description = store.description,
                address = store.address,
                pictureUrl = store.pictureUrl
            )
        )

        accountService.includeStoreToAccount(
            account.id.orEmpty(),
            entity.id.orEmpty()
        )

        return entity
    }

    fun get(id: String): Store {
        return repository.findById(id).orElseThrow {
            throw RuntimeException("Store with id $id not found")
        }
    }

    fun get(account: Account): List<Store> {
        return repository.findAllById(
            account.accountContext.map {
                it.store.id.orEmpty()
            }
        )
    }
}