package com.yeongenn.kopringstudy.kopring.domain.entity

import jakarta.persistence.*

@Entity
class UserLoanHistory(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val bookTitle: String,
    var isReturn: Char = 'N',

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    fun doReturn() {
        this.isReturn = 'Y'
    }
}