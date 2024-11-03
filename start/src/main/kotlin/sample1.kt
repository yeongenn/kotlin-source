package com.example

fun main(){
    helloWorld()

    println(add(4, 5))

    // 3. String Template
    val name = "yeongeun"
    val lastName = "Ham"
    println("my name is $name")
    println("my name is ${name}and I'm korean")
    println("my name is ${name + lastName}")

    println("is this true? ${1 == 0}")
    println("it costs 2\$")

    // 4. comments - same as JAVA
}

// 1. 함수
// 리턴 타입에 상관없이 fun 으로 시작
fun helloWorld(){
    println("Hello World")
}
// 원래라면 리턴값이 없으면
//fun helloWorld() : Unit {}
// java의 void와 같은 의미
// 근데 리턴값 없으면 생략해도 무방

// 파라미터가 있고 리턴값이 있는 함수
// fun 함수이름(파라미터 1 : 파라미터타입, 파라미터 2 : 파라미터타입, ...) : 리턴타입 {}

// 두 수를 더하는 함수
fun add(a : Int, b : Int) : Int {
    return a + b
}

// 2. val과 var
// val - value, 변하지 않는 값
// var - variable, 변수
fun hi(){
    val a : Int = 10
    var b : Int = 9

    var c = 100 // 타입 자동 추론

    // c = "hello"  // type mismatch

    // but 초기값 설정 없이 선언만 한다면 타입 명시해야
    var d : String

    // ...

    d = "kotlin practice"


}
