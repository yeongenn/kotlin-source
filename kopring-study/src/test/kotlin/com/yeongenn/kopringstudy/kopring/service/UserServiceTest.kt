package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.UserCreateRequest
import com.yeongenn.kopringstudy.kopring.domain.dto.UserUpdateRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.User
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import com.yeongenn.kopringstudy.kopring.repository.UserLoanHistoryRepository
import com.yeongenn.kopringstudy.kopring.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository
){

    @Autowired
    private lateinit var userLoanHistoryRepository: UserLoanHistoryRepository

//    @AfterEach
//    fun clean(){
//        userRepository.deleteAll()
//    }

    @Test
    @DisplayName("유저 추가 체크")
    fun addUserTest(){
        // given
        val req = UserCreateRequest("user1", "user1pw", "user1@test.com", "stella")

        // when
        userService.addUser(req)

        // then
//        val user = userRepository.findById(req.userId)
//        assertThat(user.get().userId).isEqualTo("user1")
//        assertThat(user.get().name).isEqualTo("stella")
        val users = userRepository.findAll()
        assertThat(users).hasSize(1)
        assertThat(users[0].name).isEqualTo("stella")
        assertThat(users[0].email).isEqualTo("user1@test.com")

        //userRepository.deleteAll()

    }

    @Test
    @DisplayName("전체 유저 조회 체크")
    fun getUserTest(){
        // given
        userRepository.saveAll(listOf(
//            User("user1@test.com", "user1pw", "stella", "user1"),
//            User("user2@test.com", "user2pw", "yeong", "user2"),
//            User("user3@test.com", "user3pw", "alice", "user3")
            User("user1@test.com", "user1pw", "stella", mutableListOf(),"user1"),
            User("user2@test.com", "user2pw", "yeong", mutableListOf(),"user2"),
            User("user3@test.com", "user3pw", "alice", mutableListOf(),"user3")
        ))

        // when
        val users = userService.getUsers()

        // then
        assertThat(users).hasSize(3)
        assertThat(users).extracting("name").containsExactlyInAnyOrder("stella", "alice", "yeong")

        //userRepository.deleteAll()

    }

    @Test
    @DisplayName("유저 수정 체크")
    fun updateUserTest(){
        // given
        val savedUser = userRepository.save(User("user1@test.com", "user1pw", "stella", mutableListOf(),"user1"))
        val req = UserUpdateRequest(savedUser.userId!!, "eugene", "user1@test.com", "user1pw")

        // when
        userService.updateUser(req)

        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("eugene")

    }

    @Test
    @DisplayName("유저 삭제 체크")
    fun deleteUserTest(){
        // given
        userRepository.save(User("user1@test.com", "user1pw", "stella", mutableListOf(),"user1"))

        // when
        userService.deleteUser("user1")

        // then
        assertThat(userRepository.findAll()).isEmpty()
    }

    /*
    대출 이력 조회 테스트
    1. 사용자가 지금까지 한번도 책 빌리지 않은 경우
    2. 사용자가 책을 빌리고 아직 반납하지 않은 겨웅
    3. 사용자가 책을 빌리고 반납한 경우
    4. 사용자가 책 여러권을 빌렸는데, 반납을 한 책도 있고 아닌 책도 있는 경우
     */
    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함")
    fun getUserLoanHistoriesTest1(){
        // given
        userRepository.save(User("user1@test.com", "user1pw", "stella", mutableListOf(),"user1"))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("stella")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작") // 4번을 통해 2, 3번은 자동으로 체크된다
    fun getUserLoanHistoriesTest4(){
        // given
        val savedUser = userRepository.save(User("user1@test.com", "user1pw", "stella", mutableListOf(),"user1"))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(savedUser, "book1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "book2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "book3", UserLoanStatus.RETURNED),
        ))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("stella")
        assertThat(results[0].books).hasSize(3)
        assertThat(results[0].books).extracting("title").containsExactlyInAnyOrder("book1", "book2", "book3")
        assertThat(results[0].books).extracting("isReturn").containsExactlyInAnyOrder(false, false, true)
    }

    // 위 두 테스트 코드를 합칠 수도 있지만, 복잡한 테스트 1개보다는 간단한 테스트 2개가 유지보수에는 용이하겠지?



}