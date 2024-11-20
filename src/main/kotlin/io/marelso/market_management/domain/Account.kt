package io.marelso.market_management.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class  Account(
    val id: String? = null,
    val pictureUrl: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    @JvmField val password: String,
    val accountContext: List<StorePermission> = listOf()
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String = password

    override fun getUsername(): String = email
}
