package com.charm.wonderlandshop.kopring

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTest {

    companion object {
        @JvmStatic
        @BeforeAll // 모든 테스트 수행 전 최초 1회 실행되는 메서드 지정, @JvmStatic과 함께 사용해야
        fun beforeAll() {
            println("모든 테스트 시작 전")
        }

        @JvmStatic
        @AfterAll // 모든 테스트 수행 후 최초 1회 실행되는 메서드 지정, @JvmStatic과 함께 사용해야
        fun afterAll() {
            println("모든 테스트 실행 후")
        }

    }

    @BeforeEach // 각 테스트 메서드 수행 전 실행되는 메서드 지정
    fun beforeEach() {
        println("각 테스트 시작 전")
    }

    @AfterEach // 각 테스트 메서드 수행 후 실행되는 메서드 지정
    fun afterEach() {
        println("각 테스트 실행 후")
    }

    @Test // 테스트 메서드 지정, 실행 과정에서 오류가 없으면 성공
    fun test1() {
        println("테스트 1")
    }

    @Test
    fun test2() {
        println("테스트 2")
    }
}