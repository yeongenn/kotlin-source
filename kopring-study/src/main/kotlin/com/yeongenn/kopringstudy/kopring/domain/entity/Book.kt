package com.yeongenn.kopringstudy.kopring.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Book(
    val title: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    init {
        if (title.isBlank()) {
            throw IllegalArgumentException("title cannot be empty.")
        }
    }
}