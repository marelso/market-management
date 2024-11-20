package io.marelso.market_management.utils

import io.marelso.market_management.config.SecurityConfiguration.Companion.AUTH_WHITELIST

fun String.isWhiteList() = AUTH_WHITELIST.contains(this)

fun String.containsBearerToken() = this.startsWith("Bearer ")

fun String.getToken() = this.substringAfter("Bearer ")