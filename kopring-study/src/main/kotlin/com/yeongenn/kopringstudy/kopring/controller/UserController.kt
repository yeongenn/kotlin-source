package com.yeongenn.kopringstudy.kopring.controller

import com.yeongenn.kopringstudy.kopring.domain.dto.request.UserCreateRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.request.UserUpdateRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.response.CommonResponseDto
import com.yeongenn.kopringstudy.kopring.domain.dto.response.UserLoanHistoryResponse
import com.yeongenn.kopringstudy.kopring.domain.dto.response.UserResponse
import com.yeongenn.kopringstudy.kopring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    /*
    사용자 등록하기
    [POST] /user
    @param UserCreateRequest
    @return ResponseEntity<CommonResponseDto<UserCreateRequest>>
     */
    @PostMapping("/user")
    fun addUser(@RequestBody req: UserCreateRequest): ResponseEntity<CommonResponseDto<UserCreateRequest>> {
        userService.addUser(req)
        return ResponseEntity.ok(CommonResponseDto(true, req))
    }

    /*
    전체 사용자 조회하기
    [GET] /user
    @return ResponseEntity<CommonResponseDto<List<UserResponse>>>
     */
    @GetMapping("/user")
    fun getUsers(): ResponseEntity<CommonResponseDto<List<UserResponse>>> {
        val users: List<UserResponse> = userService.getUsers()
        return ResponseEntity.ok(CommonResponseDto(true, users))
    }

    /*
    사용자 정보 수정하기
    [PUT] /user
    @param UserUpdateRequest
    @return ResponseEntity<CommonResponseDto<UserUpdateRequest>>
     */
    @PutMapping("/user")
    fun updateUserName(@RequestBody req: UserUpdateRequest): ResponseEntity<CommonResponseDto<UserUpdateRequest>> {
        userService.updateUser(req)
        return ResponseEntity.ok(CommonResponseDto(true, req))
    }

    /*
    사용자 삭제하기
    [DELETE] /user
    @param String
    @return ResponseEntity<CommonResponseDto<String>>
     */
    @DeleteMapping("/user")
    fun deleteUser(userId: String): ResponseEntity<CommonResponseDto<String>> {
        userService.deleteUser(userId)
        return ResponseEntity.ok(CommonResponseDto(true, "${userId}님 삭제 완료"))
    }

    /*
    대출 현황 조회하기
    [GET] /user/loan
    @return ResponseEntity<CommonResponseDto<List<UserLoanHistoryResponse>>>
     */
    @GetMapping("/user/loan")
    fun getUserLoanHistories(): ResponseEntity<CommonResponseDto<List<UserLoanHistoryResponse>>> {
        val userLoanHistories: List<UserLoanHistoryResponse> = userService.getUserLoanHistories()
        return ResponseEntity.ok(CommonResponseDto(true, userLoanHistories))
    }
}