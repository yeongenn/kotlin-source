package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.dto.response.BookStatResponse
import com.yeongenn.kopringstudy.kopring.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository: JpaRepository<Book, Long> {

    fun findByTitle(bookTitle: String): Book?
    @Query("select new com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse(b.type, COUNT(b.id)) " +
    "from Book b group by b.type")
    fun getStats(): List<BookStatResponse>

    /*
    @Query 사용한 JPQL, Spring Data JPA 의 단점
    1. 문자열로 쿼리를 작성하기에 버그를 찾기 어렵다.
    2. 문법이 조금 달라 그때마다 검색해 찾아보아야 한다.
    3. 동적 쿼리 작성이 어렵다.
    4. 도메인 코드 변경에 취약하다.
    5. 함수 이름 구성에 제약이 있다. (의미있는 이름을 붙이기 어렵다)
    -> 이 단점 보완을 위해서 QueryDSL 함께 사용

     */
}