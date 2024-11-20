package io.marelso.market_management.service

import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.Permission
import io.marelso.market_management.domain.StorePermission
import io.marelso.market_management.domain.dto.AccountCreateDto
import io.marelso.market_management.domain.dto.AccountDto
import io.marelso.market_management.domain.factory.AccountFactory
import io.marelso.market_management.repository.AccountRepository
import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val repository: AccountRepository,
    private val factory: AccountFactory,
    @Lazy private var storeService: StoreService,
    private val passwordEncoder: PasswordEncoder
) {

    fun save(request: AccountCreateDto): AccountDto {
        request.takeIf {
            repository.findByEmail(request.email) == null
        }?.let {
            return factory.from(
                repository.save(
                    Account(
                        firstName = request.firstName,
                        lastName = request.lastName,
                        pictureUrl = request.pictureUrl,
                        email = request.email,
                        password = passwordEncoder.encode(request.password)
                    )
                )
            )
        } ?: throw RuntimeException("Username already created")
    }

    private fun findById(id: String) = repository
        .findByIdOrNull(id) ?: throw RuntimeException("Account with id $id not found")

    fun get(id: String): AccountDto {
        return factory.from(findById(id))
    }

    fun includeStoreToAccount(id: String, storeId: String): AccountDto {
        val store = storeService.get(storeId)
        val account = findById(id)

        return factory.from(
            repository.save(
                account.copy(
                    accountContext = mutableListOf<StorePermission>().apply {
                        addAll(account.accountContext)
                        add(
                            StorePermission(
                                store = store,
                                permission = Permission.EMPLOYEE
                            )
                        )
                    }
                )
            )
        )
    }

}