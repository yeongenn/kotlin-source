package com.yeongenn.kopringstudy.kopring.domain.dto.request

data class UserCreateRequest(
    val userId: String,
    val password: String,
    val email: String,
    val name: String
)
