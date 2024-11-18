package io.marelso.market_management.service

import io.marelso.market_management.domain.Account
import io.marelso.market_management.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountSecurityService(private val repository: AccountRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): Account {
        return repository.findByEmail(username) ?: throw UsernameNotFoundException(username)
    }
}