package com.yeongenn.kopringstudy.kopring.domain.dto

data class UserUpdateRequest(
    val userId: String, // 실습이라 id 직접 맵핑
    val name: String,
    val email: String,
    val password: String
)
