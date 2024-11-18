package io.marelso.market_management.domain.factory

import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.dto.AccountDto
import org.springframework.stereotype.Component

@Component
class AccountFactory {
    fun from(account: Account, jwt: String): AccountDto {
        return AccountDto(
            reference = account.id,
            firstName = account.firstName,
            lastName = account.lastName,
            pictureUrl = account.pictureUrl,
            email = account.email,
            jwt = jwt
        )
    }
}