package com.example

fun main(){
    checkNumber(80)
}

// 4. 조건식
// 두 수 비교
fun maxBy(a : Int, b : Int) : Int {
    if(a > b) {
        return a
    } else {
        return b
    }
}

// Java의 삼항 연산자와 유사
fun maxBy2(a : Int, b : Int) = if (a > b) a else b

// Java의 switch - when
fun checkNumber(score : Int) {
    // (1)
    when(score) {
        0 -> println("this is 0")
        1 -> println("this is 1")
        // 2 혹은 3일 때
        2, 3 -> println("this is 2 or 3")
        // 위 조건에 모두 맞지 않을 때
        // 이 때의 else는 생략 가능
        //else -> println("none of these")
    }

    // (2)
    var b = when (score){
        1 -> 1
        2 -> 2
        // 이런 경우에는 명시해줘야 한다
        else -> 3
    }

    println("b : ${b}")

    // (3)
    when(score){
        in 90..100 -> println("You are genius")
        in 10..80 -> println("Not bad")
        else -> println("ok")
    }
}

// Expression vs Statement
// 뭔가 뚝딱해서 값을 만들어내면 expression
// (1) when의 경우 값 X, println을 실행 O -> 표현식 X
// (2) when의 경우 값을 리턴 -> 표현식 O

// 코틀린의 모든 함수는 표현식에 해당, 리턴값이 없는 Unit의 경우도 마찬가지
// 반면 자바 void의 경우 statement에 해당
