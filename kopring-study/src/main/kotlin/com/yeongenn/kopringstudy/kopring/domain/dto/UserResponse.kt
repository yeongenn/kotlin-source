package com.yeongenn.kopringstudy.kopring.domain.dto

import com.yeongenn.kopringstudy.kopring.domain.entity.User

data class UserResponse(
    val userId: String,
    val name: String,
    val email: String
) {
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                userId = user.userId!!,
                name = user.name,
                email = user.email
            )
        }
    }
}
