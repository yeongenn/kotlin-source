package com.yeongenn.kopringstudy.kopring.controller

import com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse
import com.yeongenn.kopringstudy.kopring.domain.dto.CommonResponseDto
import com.yeongenn.kopringstudy.kopring.service.BookService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController @Autowired constructor(
    private val bookService: BookService
) {

    /*
    현재 대여 중인 책 권수
    [GET] /book/loan
    @return ResponseEntity<CommonResponseDto<Int>>
     */
    @GetMapping("/book/loan")
//    fun countLoanedBook(): Int {
//        return bookService.countLoanedBook()
//    }
    fun countLoanedBook(): ResponseEntity<CommonResponseDto<Int>> {
        val count: Int = bookService.countLoanedBook()
        return ResponseEntity.ok(CommonResponseDto(true, count))
    }

    /*
    분야별로 등록되어 있는 책 권수
    [GET] /book/stat
    @return ResponseEntity<CommonResponseDto<Int>>
     */
    @GetMapping("/book/stat")
    fun getBookStatistics(): ResponseEntity<CommonResponseDto<List<BookStatResponse>>> {
        val bookStatList: List<BookStatResponse> = bookService.getBookStatistics()
        return ResponseEntity.ok(CommonResponseDto(true, bookStatList))
    }

}