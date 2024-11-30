package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}