package com.yeongenn.kopringstudy.kopring.domain.entity

import jakarta.persistence.*

@Entity
class UserLoanHistory(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val bookTitle: String,

    //var isReturn: Char = 'N',
    @Enumerated(EnumType.STRING)
    var status: UserLoanStatus = UserLoanStatus.LOANED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    fun doReturn() {
        //this.isReturn = 'Y'
        this.status = UserLoanStatus.RETURNED
    }

    companion object {
        fun fixture(
            user: User,
            bookTitle: String = "Alice in wonderland",
            status: UserLoanStatus = UserLoanStatus.LOANED,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookTitle = bookTitle,
                status = status,
            )
        }
    }
}

enum class UserLoanStatus {
    RETURNED, // 반납 완료
    LOANED // 대출 중
}