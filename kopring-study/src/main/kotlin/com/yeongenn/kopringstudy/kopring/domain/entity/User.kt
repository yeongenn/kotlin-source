package com.yeongenn.kopringstudy.kopring.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
class User(
    var email: String,
    var password: String,
    var name: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),

    @Id
    val userId: String? = null
) {

    /*
    Kotlin에서 JPA Entity 선언하는 방식은 사바사, 팀바팀 - 개발 컨벤션 확인하기
    다만 주의할 것
    - JPA Entity에서 setter는 안티 코드
    - data class 사용은 지양하기
    - 애초에 Kotlin이랑 JPA는 궁합이 좋은 조합은 아니다!

    이 실습의 경우 생성자에서 프로퍼티 선언해주고 setter 사용을 최대한 지양하는 방식

     */

    fun changeEmail(newEmail: String) {
        this.email = newEmail
    }

    fun changePassword(newPassword: String) {
        this.password = newPassword
    }

    fun changeName(newName: String) {
        this.name = newName
    }

    fun loanBook(book: Book) {
        this.userLoanHistories.add(UserLoanHistory(this, book.title))
    }

    fun returnBook(bookTitle: String) {
        this.userLoanHistories.first { history -> history.bookTitle == bookTitle }.doReturn()
    }
}