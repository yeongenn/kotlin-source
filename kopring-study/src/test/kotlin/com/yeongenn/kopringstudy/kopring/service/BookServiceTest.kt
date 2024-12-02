package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.BookLoanRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.BookReturnRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.Book
import com.yeongenn.kopringstudy.kopring.domain.entity.BookType
import com.yeongenn.kopringstudy.kopring.domain.entity.User
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.repository.BookRepository
import com.yeongenn.kopringstudy.kopring.repository.UserLoanHistoryRepository
import com.yeongenn.kopringstudy.kopring.repository.UserRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.assertThrows

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val userRepository: UserRepository
) {

    // (02.12.24) Book 객체 type 필드 추가, Object Mother 생성에 따른 테스트 코드 수정

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll() // CascadeType.ALL로 해놔서 자식 테이블 데이터도 다 삭제해준다
    }

    @Test
    @DisplayName("책 등록 체크")
    fun addBookTest() {
        // given
        //val req = BookRequest("Alice in wonderland", "LITERATURE") // type 추가
        val req = BookRequest("Alice in wonderland", BookType.LITERATURE)

        // when
        bookService.addBook(req)

        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].title).isEqualTo("Alice in wonderland")
        assertThat(books[0].type).isEqualTo(BookType.LITERATURE)
    }

    @Test
    @DisplayName("책 대출 체크")
    fun loanBookTest() {
        // given
        //bookRepository.save(Book("Alice in wonderland"))
        bookRepository.save(Book.fixture()) // test fixture
        val addedUser = userRepository.save(User("user1@test.com", "user1pw", "manon", mutableListOf(), "user1"))
        val req = BookLoanRequest("user1", "Alice in wonderland")

        // when
        bookService.loanBook(req)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookTitle).isEqualTo("Alice in wonderland")
        assertThat(results[0].user.userId).isEqualTo(addedUser.userId)
        assertThat(results[0].isReturn).isEqualTo('N')
    }

    @Test
    @DisplayName("책 이미 대출 시, 신규 대출 실패")
    fun loanBookFailTest() {
        // given
        //bookRepository.save(Book("Alice in wonderland"))
        bookRepository.save(Book.fixture()) // test fixture
        val addedUser = userRepository.save(User("user1@test.com", "user1pw", "manon", mutableListOf(), "user1"))
        userLoanHistoryRepository.save(UserLoanHistory(addedUser, "Alice in wonderland", 'N'))
        val req = BookLoanRequest("user1", "Alice in wonderland")

        // when & then
        assertThrows<java.lang.IllegalArgumentException> {
            bookService.loanBook(req)
        }.apply {
            assertThat(message).isEqualTo("this book is not yet returned.")
        }
    }

    @Test
    @DisplayName("책 반납 체크")
    fun returnBookTest() {
        // given
        //bookRepository.save(Book("ABC"))
        bookRepository.save(Book.fixture()) // test fixture
        val addedUser = userRepository.save(User("user1@test.com", "user1pw", "manon", mutableListOf(), "user1"))
        userLoanHistoryRepository.save(UserLoanHistory(addedUser, "ABC", 'N'))
        val req = BookReturnRequest("user1", "ABC")

        // when
        bookService.returnBook(req)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].isReturn).isEqualTo('Y')
    }
}