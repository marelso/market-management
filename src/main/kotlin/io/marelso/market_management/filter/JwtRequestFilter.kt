package io.marelso.market_management.filter

import io.marelso.market_management.config.SecurityConfiguration.Companion.AUTH_WHITELIST
import io.marelso.market_management.service.AccountSecurityService
import io.marelso.market_management.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtRequestFilter(
    private val jwtUtil: TokenService,
    private val userDetailsService: AccountSecurityService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization").orEmpty()

        if (header.containsBearerToken() && request.requestURI.isWhiteList().not()) {
            val jwtToken = header.getToken()
            val username = jwtUtil.extractEmail(jwtToken)

            if (SecurityContextHolder.getContext().authentication == null) {
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

                if (jwtUtil.isValid(jwtToken, userDetails)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun String.isWhiteList() = AUTH_WHITELIST.contains(this)

    private fun String.containsBearerToken() = this.startsWith("Bearer ")

    private fun String.getToken() = this.substringAfter("Bearer ")
}