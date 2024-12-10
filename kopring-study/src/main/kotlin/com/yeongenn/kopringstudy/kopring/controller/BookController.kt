package com.yeongenn.kopringstudy.kopring.controller

import com.yeongenn.kopringstudy.kopring.domain.dto.*
import com.yeongenn.kopringstudy.kopring.service.BookService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.CookieManager

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
        //val bookStatList: List<BookStatResponse> = bookService.getBookStatistics_v3()
        //val bookStatList: List<BookStatResponse> = bookService.getBookStatistics_v4()
        val bookStatList: List<BookStatResponse> = bookService.getBookStatistics_v5()
        return ResponseEntity.ok(CommonResponseDto(true, bookStatList))
    }

    /*
    책 등록
    [POST] /book
    @param BookRequest
    @return ResponseEntity<CommonResponseDto<String>>
     */
    @PostMapping("/book")
    fun addBook(@RequestBody req: BookRequest): ResponseEntity<CommonResponseDto<String>> {
        bookService.addBook(req)
        return ResponseEntity.ok(CommonResponseDto(true, "책 등록 완료"))
    }

    /*
    책 대출하기
    [POST] /book/loan
    @param BookLoanRequest
    @return ResponseEntity<CommonResponseDto<String>>
     */
    @PostMapping("/book/loan")
    fun loanBook(@RequestBody req: BookLoanRequest): ResponseEntity<CommonResponseDto<String>> {
        bookService.loanBook(req)
        return ResponseEntity.ok(CommonResponseDto(true, "책 대출 완료"))
    }

    /*
    책 반납하기
    [PUT] /book/return
    @param BookReturnRequest
    @return ResponseEntity<CommonResponseDto<String>>
     */
    @PutMapping("/book/return")
    fun returnBook(@RequestBody req: BookReturnRequest): ResponseEntity<CommonResponseDto<String>> {
        bookService.returnBook(req)
        return ResponseEntity.ok(CommonResponseDto(true, "책 반납 완료"))
    }



}