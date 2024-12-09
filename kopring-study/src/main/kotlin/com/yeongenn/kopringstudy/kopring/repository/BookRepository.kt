package com.yeongenn.kopringstudy.kopring.repository

import com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse
import com.yeongenn.kopringstudy.kopring.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository: JpaRepository<Book, Long> {

    fun findByTitle(bookTitle: String): Book?
    @Query("select new com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse(b.type, COUNT(b.id)) " +
    "from Book b group by b.type")
    fun getStats(): List<BookStatResponse>
}