package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.AccountDto
import io.marelso.market_management.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val service: AuthenticationService) {
    @PostMapping
    fun auth(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String
    ) = ResponseEntity.ok<AccountDto>(service.authenticate(email, password))
}