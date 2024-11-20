package io.marelso.market_management.domain

enum class Permission(private val permissionId: String, private val permissions: List<String>) {
    ADMIN("ADMIN", listOf("EMPLOYEE", "MANAGER", "ADMIN")),
    MANAGER("MANAGER", listOf("EMPLOYEE", "MANAGER")),
    EMPLOYEE("EMPLOYEE", listOf("EMPLOYEE")),
    NONE("NONE", listOf());

    fun hasPermission(permission: Permission): Boolean {
        return permissions
            .stream()
            .anyMatch { p: String -> p == permission.permissionId }
    }

    fun match(id: String): Boolean {
        return permissionId == id
    }
}