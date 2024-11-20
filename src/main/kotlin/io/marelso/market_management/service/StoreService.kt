package io.marelso.market_management.service

import io.marelso.market_management.domain.Store
import io.marelso.market_management.domain.dto.CreateStoreDto
import io.marelso.market_management.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val repository: StoreRepository) {
    fun create(store: CreateStoreDto): Store = repository.save(
        Store(
            name = store.name,
            description = store.description,
            address = store.address,
            pictureUrl = store.pictureUrl
        )
    )

    fun get(id: String): Store {
        return repository.findById(id).orElseThrow {
            throw RuntimeException("Store with id $id not found")
        }
    }

    fun getUserStores(id: String): List<Store> {
        return repository.findAllById(listOf(id))
    }
}