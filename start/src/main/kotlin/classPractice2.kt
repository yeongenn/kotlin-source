package com.example


//class Cake constructor (name : String){
//    val name = name

// 위 두 줄을 합쳐서 밑에 처럼 작성 가능
//class Cake constructor (val name : String){

// constructor 생략 가능
// 기본값 설정 가능
open class Cake(val name: String = "chocolate") {
// 기본값 설정 시 기본값 생성자 + 빈 생성자 생성(?)

    // 생성자에서는 코드블럭 실행이 안된다
    // 그럼 코드블럭을 실행하고 싶을 때는? -> init
    // init - 처음 클래스 인스턴스 생성할 때 어떤 동작 수행하고 싶은지 작성
    // init도 주 생성자의 일부라서 객체 생성됨과 동시에 실행
    init {
        println("Cake Time~!~!")
    }

    // java의 경우 오버로딩 통해서 생성자 여럿 생성
    // kotlin에서는 constructor 사용
    //constructor(name : String, ingredient : String){   }    // 에러 - constructor는 항상 주생성자의 위임을 받아야 한다
    constructor(name : String, ingredient : String) : this(name) {  // this 예약어 사용해서 상속받기
        println("I need ${ingredient} when making ${name} cake!")
    }

    open fun eatingCake() {
        println("this is so yummy!")
    }

}

// 클래스 상속
// 코틀린의 클래스는 기본적으로 final - 같은 파일 내에 있더라도 접근 불가
// 따라서 open을 해줘야 접근 가능한 클래스가 된다
class Korean : Cake(){
    // 오버라이딩
    override fun eatingCake(){  // 함수도 마찬가지로 접근 가능하게 만들려면 open 해줘야한다
        super.eatingCake()
        println("this is super yummy~!")
        println("I want to eat ${name} cake!")  // 부모 클래스의 프로퍼티 사용 가능
    }
}

fun main() {
    val cake1 = Cake("red velvet")
    println("this is ${cake1.name} cake!")
    cake1.eatingCake()

    val cake2 = Cake()  // 기본값을 줬으니까 빈 생성자 가능
    println("this is ${cake2.name} cake!")
    cake2.eatingCake()

    val cake3 = Cake("strawberry", "fresh strawberries")
    cake3.eatingCake()

    val korean1 = Korean()
    korean1.eatingCake()

}

