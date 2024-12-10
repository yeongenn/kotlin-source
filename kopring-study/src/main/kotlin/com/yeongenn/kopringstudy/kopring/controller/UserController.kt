package com.yeongenn.kopringstudy.kopring.controller

import com.yeongenn.kopringstudy.kopring.domain.dto.*
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

    @PostMapping("/user")
    fun addUser(@RequestBody req: UserCreateRequest): ResponseEntity<CommonResponseDto<UserCreateRequest>> {
        userService.addUser(req)
        return ResponseEntity.ok(CommonResponseDto(true, req))
    }

    @GetMapping("/user")
    fun getUsers(): ResponseEntity<CommonResponseDto<List<UserResponse>>> {
        val users: List<UserResponse> = userService.getUsers()
        return ResponseEntity.ok(CommonResponseDto(true, users))
    }

    @PutMapping("/user")
    fun updateUserName(@RequestBody req: UserUpdateRequest): ResponseEntity<CommonResponseDto<UserUpdateRequest>> {
        userService.updateUser(req)
        return ResponseEntity.ok(CommonResponseDto(true, req))
    }

    @DeleteMapping("/user")
    fun deleteUser(userId: String): ResponseEntity<CommonResponseDto<String>> {
        userService.deleteUser(userId)
        return ResponseEntity.ok(CommonResponseDto(true, "${userId}님 삭제 완료"))
    }

    @GetMapping("/user/loan")
    fun getUserLoanHistories(): ResponseEntity<CommonResponseDto<List<UserLoanHistoryResponse>>> {
        val userLoanHistories: List<UserLoanHistoryResponse> = userService.getUserLoanHistories()
        return ResponseEntity.ok(CommonResponseDto(true, userLoanHistories))
    }
}