package io.marelso.market_management.service

import io.marelso.market_management.config.TokenProperties
import io.marelso.market_management.domain.Account
import io.marelso.market_management.domain.dto.AccountDto
import io.marelso.market_management.domain.factory.AccountFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val accountFactory: AccountFactory,
    private val accountSecurityService: AccountSecurityService,
    private val tokenService: TokenService,
    private val tokenProperties: TokenProperties,
) {
    fun authenticate(email: String, password: String): AccountDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                email,
                password
            )
        )

        val account = accountSecurityService.loadUserByUsername(email)
        val token = tokenService.generate(
            account = account,
            expirationDate = Date(System.currentTimeMillis() + tokenProperties.accessTokenExpiration),
        )

        return accountFactory.from(account, token)
    }

    fun extractAccountFromToken(token: String) = accountSecurityService.loadUserByUsername(
        tokenService.extractEmail(token)
    )
}