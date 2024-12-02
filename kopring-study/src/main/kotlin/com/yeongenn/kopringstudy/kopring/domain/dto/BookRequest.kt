package com.yeongenn.kopringstudy.kopring.domain.dto

import com.yeongenn.kopringstudy.kopring.domain.entity.BookType

data class BookRequest(
    val title: String,
    //val type: String // 책 분류 추가
    val type: BookType
)
