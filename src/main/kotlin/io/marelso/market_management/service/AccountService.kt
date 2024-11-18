package io.marelso.market_management.service

import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.dto.AccountCreateDto
import io.marelso.market_management.domain.dto.AccountDto
import io.marelso.market_management.repository.AccountRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AccountService(
    private val repository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun save(request: AccountCreateDto): Account {
        request.takeIf {
            repository.findByEmail(request.email) == null
        }?.let {
            return repository.save(
                Account(
                    firstName = request.firstName,
                    lastName = request.lastName,
                    pictureUrl = request.pictureUrl,
                    email = request.email,
                    password = passwordEncoder.encode(request.password)
                )
            )
        } ?: throw RuntimeException("Username already created")
    }

    fun get(id: String): AccountDto {
        repository.findByIdOrNull(id)?.let {
            return AccountDto(
                reference = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                pictureUrl = it.pictureUrl,
                email = it.email
            )
        } ?: throw RuntimeException("Account with id $id not found")
    }

}