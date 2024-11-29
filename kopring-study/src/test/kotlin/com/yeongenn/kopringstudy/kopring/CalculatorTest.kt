package com.charm.wonderlandshop.kopring

class CalculatorTest {

    fun addTest(){
        // given : 테스트 대상을 만들어 준비하는 과정
        val calculator = Calculator(5)
        // when : 실제 우리가 테스트 하고 싶은 기능 호출하는 과정
        calculator.add(3)

        // 연산 결과값 8을 계산기가 가지고 있는지 확인

        /*
        1. Calculator.kt 를 data class 로 만들고 equals를 통해서 비교
            data class로 선언하면 equals 자동 생성해준다
         */
//        val expectedResult = Calculator(8)
//        if(calculator != expectedResult){
//            throw IllegalArgumentException()
//        }

        /*
        2. Calculator로부터 number 가져오기
            2-1. 접근제어자 private -> public
            2-2. 원래 존재하던 number 프로퍼티를 _number로 이름 변경하고 불변 number 추가
                (Kotlin에서는 이를 backing property라고 하며, backing property에는 _ 사용이 관례)
            2-1 방법은 setter가 노출된다는 단점, 2-2 방법은 코드가 장황해진다는 단점
         */
        // then : 호출 이후 의도한대로 결과가 나왔는지 확인하는 과정
        if(calculator.number != 8){ // 2-2. 1번 방법보다 코드가 훨씬 간결해진걸 확인할 수 있다
            throw IllegalArgumentException()
        }
    }

    fun divideTest(){
        /*
        나눗셈은 다른 연산과 다르게 예외가 있기 때문에 2가지로 테스트
        1. 0이 아닌 다른 숫자를 넣었을 때 정상 작동하는지
        2. 0을 넣었을 때 우리가 의도한 예외가 발생하는지
         */

        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        if (calculator.number != 2){
            throw IllegalArgumentException()
        }
    }

    fun divideExceptionTest(){
        // given
        val calculator = Calculator(5)

        // when & then
        try {
            calculator.divide(0)
        } catch (e: java.lang.IllegalArgumentException){
            if(e.message != "cannot divide by 0"){ // 메세지 확인 작업 추가
                throw IllegalStateException("메세지가 다릅니다.")
            }
            // 테스트 통과
            return
        } catch (e: Exception){
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }

}

fun main(){
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}