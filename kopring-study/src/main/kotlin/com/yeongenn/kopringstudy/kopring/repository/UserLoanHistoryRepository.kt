package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.dto.BookLoanRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository: JpaRepository<UserLoanHistory, Long> {

    //fun findByBookTitleAndIsReturn(title: String, isReturn: Char): UserLoanHistory?
    fun findByBookTitleAndStatus(bookTitle: String, status: UserLoanStatus): UserLoanHistory?
    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
    fun countByStatus(status: UserLoanStatus): Long

    /*
    @Query 사용하지 않고 Spring Data JPA가 자동으로 만들어주는 쿼리 사용 - 이것도 Querydsl?
    Querydsl 사용 시 장점인 '동적 쿼리 간편함'
    
    예로 도서 검색 기능 생각해보자
    책 제목 뿐만 아니라 작가, 출판사, 출판년도 등 검색 필드 多 - 근데 다 선택사항이야
    그럼 Repository의 함수가 엄청나게 증가하게 된다 -> Querydsl로는 간단하게 해결 가능

     */
}