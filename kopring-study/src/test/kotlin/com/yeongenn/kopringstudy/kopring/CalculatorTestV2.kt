package com.charm.wonderlandshop.kopring

import org.junit.jupiter.api.Test
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CalculatorTestV2 { // Junit5 기반으로 테스트 코드 작성하기

    @Test
    fun addTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        // 단언문 assertThat 사용
        assertThat(calculator.number).isEqualTo(8)

    }

    @Test
    fun minusTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(5)

        // then
        assertThat(calculator.number).isEqualTo(25)
    }

    @Test
    fun divideTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest(){
        // given
        val calculator = Calculator(5)

        // when & then
//        val message = assertThrows<IllegalArgumentException> {
//            calculator.divide(0)
//        }.message
//        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")

        // scope function 활용 리팩토링
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("cannot divide by 0")
        }
    }
}