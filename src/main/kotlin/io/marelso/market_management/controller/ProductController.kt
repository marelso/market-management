package io.marelso.market_management.controller

import io.marelso.market_management.domain.dto.CreateProductDto
import io.marelso.market_management.domain.dto.ProductDto
import io.marelso.market_management.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(private val service: ProductService) {
    @PostMapping
    fun create(
        @RequestBody product: CreateProductDto
    ): ResponseEntity<ProductDto> = ResponseEntity.ok(service.create(product))

    @PutMapping("/{id}")
    fun update(
        @RequestBody product: CreateProductDto,
        @PathVariable id: String
    ): ResponseEntity<ProductDto> = ResponseEntity.ok(service.update(id, product))

    @GetMapping
    fun getByStoreId(
        @RequestParam(value = "storeId") storeId: String,
        @RequestParam(value = "query", required = false, defaultValue = "") query: String,
        pageable: Pageable
    ): ResponseEntity<Page<ProductDto>> = ResponseEntity.ok(service.getByStoreId(storeId, query, pageable))

    @GetMapping("/{productId}")
    fun getByProductId(
        @PathVariable("productId") productId: String
    ): ResponseEntity<ProductDto> = ResponseEntity.ok(service.getById(productId))
}