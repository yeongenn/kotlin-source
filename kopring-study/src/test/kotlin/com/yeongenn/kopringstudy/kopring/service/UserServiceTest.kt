package com.yeongenn.kopringstudy.kopring.service

import com.yeongenn.kopringstudy.kopring.domain.dto.UserCreateRequest
import com.yeongenn.kopringstudy.kopring.domain.entity.User
import com.yeongenn.kopringstudy.kopring.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository
){

    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }

    @Test
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
    fun getUserTest(){
        // given
        userRepository.saveAll(listOf(
            User("user1@test.com", "user1pw", "stella", "user1"),
            User("user2@test.com", "user2pw", "yeong", "user2"),
            User("user3@test.com", "user3pw", "alice", "user3")
        ))

        // when
        val users = userService.getUsers()

        // then
        assertThat(users).hasSize(3)
        assertThat(users).extracting("name").containsExactlyInAnyOrder("stella", "alice", "yeong")

        //userRepository.deleteAll()

    }


}