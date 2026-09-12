package org.quizzz.dto

data class UserRecord(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val role: String
)
