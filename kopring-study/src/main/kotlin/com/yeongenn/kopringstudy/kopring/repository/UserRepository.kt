package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, String>, UserRepositoryCustom {
//    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories") // N + 1 해소
//    fun findAllWithHistories(): List<User>

    fun findByUserId(userId: String): User?

}