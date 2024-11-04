package com.example

// Lambda
// value 처럼 다룰 수 있는 익명 함수

// value 처럼 다룰 수 있다?
// 1) 메서드의 파라미터로 넘겨 줄 수 있다
// 2) 리턴값으로 사용할 수 있다

// 기본 형식
// val/var lambdaName : Type = {argumentList -> codyBody}

// 예제 - 제곱수 리턴하기
// 두번째 (Int)는 리턴값 타입 지정, 리턴값 타입을 Int로 지정해줬기 때문에 number 타입은 자동 추론
val square: (Int) -> (Int) = { number -> number * number }

// 리턴타입 명시해주지 않았기 때문에 nameAndAge는 Unit 타입으로 잡힌다
val nameAndAge = { name: String, age: Int ->

    // 람다는 항상 codeBody 맨 마지막 줄을 리턴
    //"my name is ${name} and I'm ${age} years old."    // 리턴타입은 String이 된다
}

fun main() {
    println(square(12))
    println(nameAndAge("yeongeun", 28))

    val meSaid = "me said"
    val sheSaid = "she said"    // 둘 모두 String class 따라서 pizza() 호출 가능
    println(meSaid.pizza())
    println(sheSaid.pizza())

    println(extendString("ye", 28))

    println(calculateGrade(101))

    // 람다 사용하는 여러가지 방법 - 파라미터로 람다
    // 파라미터로 사용할 람다식 작성
    val lambda = {number : Double ->
        number == 1.234
    }

    // 파라미터로 위 람다식 넘겨주기
    println(invokeLambda(lambda))
    // 람다 리터럴
    println(invokeLambda { true })
    println(invokeLambda({ it > 1 }))   // it - 파라미터가 한개이니까

    // fun의 마지막 파라미터가 람다일 때 소괄호 생략 가능 -> 바로 중괄호 가능
    println(invokeLambda { it > 2 })
}

// 확장 함수

// String class 확장하기
val pizza: String.() -> String = {
    this + " pizza~ pizza!"
}

//
fun extendString(name: String, age: Int): String {
    val introduceMyself: String.(Int) -> String = {
        // this - 확장 함수가 call 하는 Object
        // it - 파라미터 한개 넘어올 때 it 으로 생략 가능, 이 예시에서는 Int
        "I am ${this} and ${it} years old."
    }

    return name.introduceMyself(age)
}

// 람다의 return
// 마지막 한 줄이 리턴값이 된다
val calculateGrade: (Int) -> String = {
    when (it) {
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "good"
        // 나머지 값에 대한 else 처리 안해주면 when 에 에러 표시
        // 리턴타입을 String 으로 명시했기 때문에 else 로 나머지 경우에 대한 리턴값 부여
        else -> "over 100"
    }
}

// 람다 표현하는 여러 방법 - 파라미터로 람다 사용
fun invokeLambda (lambda : (Double) -> Boolean) : Boolean {
    return lambda(1.23)
}
// 설명(?)
// invokeLambda 함수는 boolean을 리턴하는 함수
// 어떤 람다식을 사용하냐? -> 파라미터로 넣은 람다식을 사용
// 그 람다에 1.23을 넣어서 리턴되는 boolean 값을 invokeLambda의 리턴값으로 삼겠다

// 람다 - 익명 내부 함수
// 이 부분은 찾아보면서 따로 공부하기
