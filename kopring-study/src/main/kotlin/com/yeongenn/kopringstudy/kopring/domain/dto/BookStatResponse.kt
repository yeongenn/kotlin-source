package com.yeongenn.kopringstudy.kopring.domain.dto

import com.yeongenn.kopringstudy.kopring.domain.entity.BookType

data class BookStatResponse(
    val type: BookType,
    //val count: Int,
    val count: Long,
) {
//    fun plusOne() {
//        this.count += 1
//    }
}
