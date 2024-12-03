package io.marelso.market_management.controller

import io.marelso.market_management.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionController(private val service: TransactionService) {
    @PostMapping("/buy")
    fun buyProducts() {

    }
}