package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositoryCustom {

    //fun findByUserId(userId: String): User?

    fun findAllWithHistories(): List<User>
}