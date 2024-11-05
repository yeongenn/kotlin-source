package com.example

// 접근제어자
// 자바의 경우 public, protected, default, private

// 코틀린의 경우
// 1. protected : 선언된 클래스 또는 하위 클래스에서만 접근 가능
// 2. internal : 같은 모듈에서만 접근 가능하므로 상위 모듈은 하위 모듈에 접근이 불가

// 모듈?
// 한번에 컴파일되는 Kotlin 코드

// 자바에서는 default 가 기본 접근 제어자, 코틀린은 public 이 기본

// 가시성 제어
// 1. 생성자
// 생성자에 접근 제어자를 붙이려면, constructor 사용해야
open class Cat protected constructor (

)

// 자바에서는 유틸성 코드를 만들 때 인스턴스화를 막기 위해 abstract + private 생성자 사용
// 코틀린도 똑같이 가능하지만, 더 간단하게 파일 최상단에 유틸 함수를 작성하기만 하면
// public static final 이 자동으로 붙게 되어 정적 메서드와 같이 사용할 수 있다
fun isDirectoryPath(path : String) : Boolean {  // public static final
    return path.endsWith("/")
}

// 2. 프로퍼티
// getter 는 허용하고 setter 는 private 로 막고 싶은 경우
// custom setter 를 활용해 setter 에만 추가로 가시성을 부여해 줄 수 있다
class Car(
    internal val name : String, // getter 와 setter 한번에 접근 제어
    private var owner : String,
    _price : Int
) {
    var price = _price  // setter 만 접근 제어
    private set
}
// 위 코드는 3개의 getter와, name owner에 대한 2개의 setter가 존재

// 주의사항
// internal은 바이트 코드 상 public이 되기 때문에 상위 모듈의 자바 코드에서는 하위 코틀린 모듈의 internal 코드를 가져올 수 있게 된다
// 자바는 같은 패키지의 코틀린 protected 멤버에 접근할 수 있다