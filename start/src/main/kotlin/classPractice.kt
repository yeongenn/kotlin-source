package com.example


// class 정의하기
// java와 달리 파일명이랑 클래스명이 동일하지 않아도 된다
//class Human{
class Human constructor(name : String){
    
    // propertires, functions ...
    val name = name
    val cakeName = "red velvet"
    fun eatingCake(){
        println("this is so yummy~")
    }
}

fun main(){
    // 객체 생성할 때 new 필요없음
    //val human = Human()   //  name 정의 안했기 때문에 에러
    val human = Human("ye")
    println("this human's name is ${human.name}.")
    println("this is ${human.cakeName} cake!")
    human.eatingCake()

    // 만약 cakeName을 human 객체를 생성할 때 정의해주고 싶다면? -> 생성자
    // 클래스명 뒤에 constructor 명시
}