package io.marelso.market_management.service

import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.Transaction
import io.marelso.market_management.domain.TransactionType
import io.marelso.market_management.domain.dto.CartRequestDto
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.domain.dto.TransactionDto
import io.marelso.market_management.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val entryService: ProductEntryService,
) {
    fun buy(
        cart: CartRequestDto,
        account: Account
    ): TransactionDto {
        val transaction = repository.save(
            Transaction(
                storeId = cart.storeId,
                accountId = account.id.orEmpty(),
                type = TransactionType.BUYING,
                total = cart.products.sumOf { it.cost * it.quantity }
            )
        )
        val products = mutableListOf<ProductDto>()

        transaction.id?.let {
            cart.products.forEach { cartItem ->
                products.add(
                    entryService.create(
                        cart = cartItem,
                        storeId = cart.storeId,
                        accountId = account.id.orEmpty(),
                        transactionId = transaction.id
                    )
                )
            }

            return TransactionDto(
                id = transaction.id,
                type = transaction.type,
                products = products,
                total = transaction.total,
            )
        } ?: throw RuntimeException("Transaction could not be created")
    }

    fun sell(
        cart: CartRequestDto,
        account: Account
    ): TransactionDto {
        val transaction = repository.save(
            Transaction(
                storeId = cart.storeId,
                accountId = account.id.orEmpty(),
                type = TransactionType.SELLING,
                total = cart.products.sumOf { it.cost * it.quantity }
            )
        )
        val products = mutableListOf<ProductDto>()

        transaction.id?.let {
            cart.products.forEach { cartItem ->
                products.add(
                    entryService.sell(
                        cart = cartItem,
                        storeId = cart.storeId,
                        accountId = account.id.orEmpty(),
                        transactionId = transaction.id
                    )
                )
            }

            return TransactionDto(
                id = transaction.id,
                type = transaction.type,
                products = products,
                total = transaction.total,
            )
        } ?: throw RuntimeException("Transaction could not be created")
    }
}