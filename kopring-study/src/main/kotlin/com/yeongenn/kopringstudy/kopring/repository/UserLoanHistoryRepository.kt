package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.dto.BookLoanRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository: JpaRepository<UserLoanHistory, Long> {

    //fun findByBookTitleAndIsReturn(title: String, isReturn: Char): UserLoanHistory?
    fun findByBookTitleAndStatus(bookTitle: String, status: UserLoanStatus): UserLoanHistory?
    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
}