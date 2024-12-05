package com.yeongenn.kopringstudy.kopring.domain.dto

import com.yeongenn.kopringstudy.kopring.domain.entity.BookType

data class BookStatResponse(
    val type: BookType,
    var count: Int,
) {
//    fun plusOne() {
//        this.count += 1
//    }
}
