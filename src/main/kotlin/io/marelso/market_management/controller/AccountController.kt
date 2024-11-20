package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.AccountCreateDto
import io.marelso.market_management.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(private val service: AccountService) {
    @PostMapping
    fun create(@RequestBody request: AccountCreateDto) = ResponseEntity.ok(service.save(request))

    @GetMapping("/{id}")
    fun get(
        @RequestHeader("Authorization") bearerToken: String,
        @PathVariable("id") id: String
    ) = ResponseEntity.ok(service.get(id))

    @PutMapping("/{id}")
    fun includeStoreToAccount(
        @RequestHeader("Authorization") bearerToken: String,
        @PathVariable("id") id: String,
        @RequestParam("storeId") storeId: String
    ) = service.includeStoreToAccount(id, storeId)
}