package io.marelso.market_management.config

import io.marelso.market_management.filter.JwtRequestFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    companion object {
        val AUTH_WHITELIST = arrayOf(
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/**",
            "/api/v1/auth",
            "/api/v1/accounts/**",
        )
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity, authenticationFilter: JwtRequestFilter): DefaultSecurityFilterChain {
        return http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(*AUTH_WHITELIST).permitAll()
                    .anyRequest().fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}