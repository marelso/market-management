package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.CartRequestDto
import io.marelso.market_management.service.AuthenticationService
import io.marelso.market_management.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController(
    private val service: TransactionService,
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/buy")
    fun buyProducts(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestBody request: CartRequestDto
    ) = ResponseEntity.ok(
        service.buy(
            cart = request,
            account = authenticationService.extractAccountFromToken(bearerToken)
        )
    )

    @PostMapping("/sell")
    fun sellProducts(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestBody request: CartRequestDto
    ) = ResponseEntity.ok(
        service.buy(
            cart = request,
            account = authenticationService.extractAccountFromToken(bearerToken)
        )
    )
}