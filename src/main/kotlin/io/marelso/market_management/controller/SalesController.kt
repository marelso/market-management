package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.BuyProductDto
import io.marelso.market_management.service.SalesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sales")
class SalesController(private val service: SalesService) {
    @PostMapping
    fun buy(
        @RequestBody cart: BuyProductDto
    ) = ResponseEntity.ok(service.buy(cart))

    @PutMapping
    fun sell(
        @RequestBody cart: BuyProductDto
    ) = ResponseEntity.ok(service.sell(cart))
}