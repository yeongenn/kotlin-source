package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.BookLoanRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookReturnRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.Book
import com.yeongenn.kopringstudy.kopring.repository.BookRepository
import com.yeongenn.kopringstudy.kopring.repository.UserLoanHistoryRepository
import com.yeongenn.kopringstudy.kopring.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.yeongenn.kopringstudy.kopring.util.fail
import org.springframework.data.repository.findByIdOrNull

@Service
class BookService @Autowired constructor (
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    // 책 등록
    fun addBook(req: BookRequest){
        bookRepository.save(Book(req.title, req.type))
    }

    // 책 대출
    @Transactional
    fun loanBook(req: BookLoanRequest){
        if(userLoanHistoryRepository.findByBookTitleAndIsReturn(req.bookTitle, 'N') != null){
            throw IllegalArgumentException("this book is not yet returned.")
        }

        //val book = bookRepository.findByTitle(req.bookTitle) ?: throw IllegalArgumentException()
        val book = bookRepository.findByTitle(req.bookTitle) ?: fail()
        //val user = userRepository.findById(req.userId) ?: throw IllegalArgumentException()
        //user.get().loanBook(book)
        val user = userRepository.findByIdOrNull(req.userId) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(req: BookReturnRequest){
        val user = userRepository.findByIdOrNull(req.userId) ?: fail()
        user.returnBook(req.bookTitle)
    }
}