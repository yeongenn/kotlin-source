package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.BookLoanRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookReturnRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse
import com.yeongenn.kopringstudy.kopring.domain.entity.Book
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import com.yeongenn.kopringstudy.kopring.repository.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.yeongenn.kopringstudy.kopring.util.fail
import org.springframework.data.repository.findByIdOrNull

@Service
class BookService @Autowired constructor(
    private val bookRepository: BookRepository,
    private val bookQuerydslRepository: BookQuerydslRepository, // 추가
    private val userRepository: UserRepository,
    //private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val userLoanHistoryQuerydslRepository: UserLoanHistoryQuerydslRepository    // 추가
) {

    // 책 등록
    fun addBook(req: BookRequest) {
        bookRepository.save(Book(req.title, req.type))
    }

    // 책 대출
    @Transactional
    fun loanBook(req: BookLoanRequest) {
        //if(userLoanHistoryRepository.findByBookTitleAndIsReturn(req.bookTitle, 'N') != null){
        //if (userLoanHistoryRepository.findByBookTitleAndStatus(req.bookTitle, UserLoanStatus.LOANED) != null) {
        if (userLoanHistoryQuerydslRepository.find(req.bookTitle, UserLoanStatus.LOANED) != null) {
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
    fun returnBook(req: BookReturnRequest) {
        val user = userRepository.findByIdOrNull(req.userId) ?: fail()
        user.returnBook(req.bookTitle)
    }

    // 현재 대여 중인 책의 권수 보여주기
    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
        //return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
        //return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOANED).toInt()
        return userLoanHistoryQuerydslRepository.count(UserLoanStatus.LOANED).toInt()
    }

    // 분야 별로 등록되어 있는 책 권수 보여주기
    // v1 (for) - 딱 봐도 코드가 너무 길다, BookStatResponse의 count 필드가 노출
//    @Transactional(readOnly = true)
//    fun getBookStatistics_v1(): List<BookStatResponse> {
//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            val target = results.firstOrNull { target -> book.type == target.type }
//            if (target == null) {
//                results.add(BookStatResponse(book.type, 1))
//            } else {
//                target.plusOne()
//            }
//        }
//        return results
//    }

    // v2 (elvis) - 로직이 복잡, call chain이 길다, 마찬가지로 BookStatResponse의 count 필드가 노출
//    @Transactional
//    fun getBookStatistics_v2(): List<BookStatResponse> {
//        val result = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            result.firstOrNull { dto -> dto.type == book.type }?.plusOne()
//                ?: result.add(BookStatResponse(book.type, 1))
//        }
//        return result
//    }

    // v3 (groupBy)
    // 코드가 간결해지고 BookStatResponse의 count 필드가 final이 되어 의도치 않은 수정 예방
    // BUT 전체 목록을 조회해서 그 갯수를 카운트하기 때문에 DB 부하 가능성 O
//    @Transactional
//    fun getBookStatistics_v3(): List<BookStatResponse> {
//        return bookRepository.findAll()
//            .groupBy { book -> book.type } // group by 사용
//            .map { (type, books) -> BookStatResponse(type, books.size) }
//    }

    // v4 (JPQL) - 동일한 기능을 DB에서 처리하는 경우
//    @Transactional(readOnly = true)
//    fun getBookStatistics_v4(): List<BookStatResponse> {
//        return bookRepository.getStats()
//    }

    // v5 (Querydsl)
    @Transactional(readOnly = true)
    fun getBookStatistics_v5(): List<BookStatResponse> {
        return bookQuerydslRepository.getStats()
    }

}