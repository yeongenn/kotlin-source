package com.example

// 6. For / While
val array1 = arrayOf(1, 2, 3, 4, 5)

fun forAndWhile(){
    val students = arrayListOf("tony", "thor", "peter")

    for (name in students) {
        println("${name}")
    }

    // 1 ~ 10 더하기
    var sum : Int = 0
    //for (i in 1..10) {
    //for (i in 1..10 step 2){ // 격순
    //for (i in 10 downTo 1) { // 역순
    for (i in 1 until 100) { // 1 이상 100 미만

        sum += i
    }

    println(sum)

    // while
    var index = 0
//    while (index < 10){
//        println("current index : ${index}")
//        index++
//    }

    // 반복문에서 index랑 변수값 동시에 사용하기
    for((index, name) in students.withIndex()){
        println("${index + 1}번째 학생 : ${name}")
    }
}

fun main(){
    forAndWhile()
}