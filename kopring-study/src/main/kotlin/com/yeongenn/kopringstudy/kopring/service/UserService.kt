package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.BookHistoryResponse
import com.yeongenn.kopringstudy.kopring.domain.dto.UserCreateRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.UserLoanHistoryResponse
import com.yeongenn.kopringstudy.kopring.domain.dto.UserUpdateRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.User
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import com.yeongenn.kopringstudy.kopring.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.yeongenn.kopringstudy.kopring.util.fail

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {

    // 개인 조회
    fun getUser(userId: String): User {
        //val user: User = userRepository.findById(userId).orElseThrow(::IllegalArgumentException)
        val user: User = userRepository.findByIdOrNull(userId) ?: fail()
        return user;
    }

    //
    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    // 유저 추가
    @Transactional
    fun addUser(req: UserCreateRequest) {
        val newUser = User(req.email, req.password, req.name, mutableListOf(), req.userId, )
        userRepository.save(newUser)
    }

    // 유저 수정
    @Transactional
    fun updateUser(req: UserUpdateRequest) {
        //var user = userRepository.findById(req.userId).orElseThrow(::IllegalArgumentException)
        var user: User = userRepository.findByIdOrNull(req.userId) ?: fail()
        // 기존 값과 다를 시에만 수정하는 과정은 생략~
        user.changeName(req.name)
        user.changeEmail(req.email)
        user.changePassword(req.password)
        userRepository.save(user)
    }

    // 유저 삭제
    @Transactional
    fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }

    // 대출 이력 조회
    @Transactional // lazyinitializationexception 해걸하는데 좋은 방법은 아니라고 하던데..?
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        //return userRepository.findAll().map { user ->
//        return userRepository.findAllWithHistories().map { user ->
//            UserLoanHistoryResponse(
//                name = user.name,
//                books = user.userLoanHistories.map { history ->
//                    BookHistoryResponse(
//                        title = history.bookTitle,
//                        isReturn = history.status == UserLoanStatus.RETURNED
//                    )
//                }
//            )
//        }
        val users = userRepository.findAllWithHistories()
        return users.map { user -> UserLoanHistoryResponse.of(user) } // 정적 팩토리 호출로 코드 간결화
    }
}