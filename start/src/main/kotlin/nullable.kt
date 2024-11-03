package com.example

// 7. Nullable / NonNull

fun main(){
    nullCheck()
    ignoreNulls("doremi")
}

fun nullCheck(){
    // NPE - 런타임에서 잡히는 에러
    // 이게 불편하니까 코틀린에서는 컴파일 시점에서 다 잡아줄게! -> ?
    var name : String = "ye"

    //var nullName : String = null // Null cannot be a value of a non-null type String
    var nullName : String? = null

    var nameInUpperCase = name.uppercase()
    println(nameInUpperCase)

    //var nullNameInUpperCase = nullName.uppercase()  // nullName의 null 여부를 모르기 때문에 uppercase 사용 가능 여부 알 수 없음
    var nullNameInUpperCase = nullName?.uppercase() // nullName이 null이 아니면 uppercase(), null이면 null 리턴 / nullNameInUpperCase 타입은 자동 추론된다(String?)

    // ?: - Elvis 연산자
    // default 값을 주고 싶을 때

    //val lastName : String? = null
    val lastName : String? = "Ham"
    
    // lastName이 null값일 경우 default값 부여
    val fullName = name + " " + (lastName?: "NO lastName")

    println(fullName)

    // !! - 컴파일러에게 null이 아님을 알려주는 연산자(?)
    // 가급적 사용은 지양 -> NPE 일어난다
}

fun ignoreNulls(str : String?){
    val mNotNull : String = str!!   // 얘는 무조건 not null!!
    val upperCase = mNotNull.uppercase()    // mNotNull 뒤에 ? 없어도 된다 <- 무조건 not null이라고 했으니까


    val email : String? = "yeongeun@me.com"

    // let - 자신의 receiver 객체를 람다식 내부로 옮겨서 실행하는 구문
    // email이 null이 아닌 경우 let 실행 -> email이 null이 아니라면 email을 람다식 내부로 옮기기
    email?.let{
        println("my email is ${email}")
    }
}