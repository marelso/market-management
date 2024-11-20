package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.CreateStoreDto
import io.marelso.market_management.service.AuthenticationService
import io.marelso.market_management.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/stores")
class StoreController(
    private val service: StoreService,
    private val authenticationService: AuthenticationService
) {
    @PostMapping
    fun create(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestBody request: CreateStoreDto
    ) = ResponseEntity.ok(service.create(
        request
    ))

    @GetMapping("/{id}")
    fun get(
        @RequestHeader("Authorization") bearerToken: String,
        @PathVariable("id") id: String
    ) = ResponseEntity.ok(service.get(id))

    @GetMapping
    fun getUserStores(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestParam("userId") userId: String
    ) = ResponseEntity.ok(service.get(userId))
}