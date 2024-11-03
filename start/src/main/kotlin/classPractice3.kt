package com.example

// 1. 자동 프로퍼티
// val, var 통해 필드만 만들어도 getter, setter 자동으로 생성
// 각각을 프로퍼티라고 칭한다

// 클래스의 필드 선언 및 생성자, getter, setter 동시에 선언 가능
// 코드 간결성 증가

/*
class Person(
    val name : String,
    var age : Int
){

}

 */

// 프로퍼티 값 조회
// java ver. -> kotlin ver.
// 객체.getName() -> 객체.name, 객체.setName("A") -> 객체.name = "A"

// 2. 주생성자와 init
// 코틀린 생성자 - 기본, 보조
// 주 생성자는 클래스 해더에 위치, 보조 생성자는 클래스 내부에 위치

// 만약 생성자에서 검증 로직을 체크해야 하는 경우, init을 사용하면 된다
// init - 생성자가 호출되는 시점에 한번만 호출되는 초기화 블럭

class Person(
    val name : String = "ye",   // default parameter
    var age : Int = 0   // default parameter
) {
    init {
        if (age < 0){
            throw IllegalArgumentException()
        }
    }

    // 보조 생성자 - constructor
    constructor(name : String) : this(name, 1)
    
    constructor() : this("홍길동"){ // 부 생성자도 body를 가질 수 있다
        println("부 생성자")
    }

    // 보조 생성자는 최종적으로 주 생성자를 this로 호출해야 한다
    // 이러한 위임은 기본 생성자에 직접 하거나 다른 보조 생성자 통해 간접적으로도 가능
    // 본문이 호출될 때는 초기화 블럭부터 시작해 역순으로 호출된다
    
    // 부 생성자보다는 default parameter 권장
    // 만약 타입 컨버팅과 같은 경우가 필요하다면 정적 팩토리 메서드 활용할 것

    // 3. custom getter, setter
    // 함수, 프로퍼티 모두 사용 가능

    // 3-1. 함수 방식
    fun isAdultFun() : Boolean {
        return this.age >= 20
    }

    // 3-2. 프로퍼티 방식
//    val isAdult : Boolean
//        get(){
//            return this.age >= 20
//        }
    
    // 중괄호, return 제거 버전
    val isAdultVal : Boolean
        get() = this.age >= 20

    // 프로퍼티 방식 사용 시, 해당 getter와 setter에 대응되는 필드가
    // 클래스 내부에 없음에도 밖에서는 getter 함수 사용 가능
    val newName : String    // 실제 존재하는 필드가 X, 변수 선언 아님 주의!!!
        get() = this.name + " Ham"

    // backing field
}

fun main(){
    val test1 = Person()
    println(test1.isAdultFun())
    println(test1.isAdultVal)

    val test2 = Person("yeongeun" , 29)
    println(test2.isAdultFun())
    println(test2.isAdultVal)
    println(test2.newName)
}


