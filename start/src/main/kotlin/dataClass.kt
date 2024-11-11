package com.example

import java.util.Date

// data class

// POJO 클래스
// 일종의 틀 역할을 하는 클래스
// 자바의 경우 직접 일일이 작성
// 이런 boiler plate 코드를 줄이기 위해서 사용하는 것이 data class
// 말 그대로 data를 담는 class

// 티켓 정보를 담는 클래스
data class Ticket(val airline : String, val customerName : String, var date : String, val seatNumber : Int) // 이게 끝

// 이게 끝이라고?
// 변수를 선언 -> 클래스의 프로퍼티처럼 사용 가능
// 생성자도 생성
// toString 메서드도 자동 생성
// hashCode(), equals(), copy()를 자동 생성
// 클래스 바디 없이도 위의 메서드들이 생성된다

// data class와 class 차이
class TicketNormal(val airline : String, val customerName : String, var date : String, val seatNumber : Int)

fun main(){
    val ticketA = Ticket("koreanAir", "yeongeun", "2024-12-01", 24)
    //println(ticketA.toString())

    val ticketB = TicketNormal("koreanAir", "yeongeun", "2024-12-01", 24)
    println(ticketA) // Ticket(airline=koreanAir, customerName=yeongeun, date=2024-12-01, seatNumber=24)
    println(ticketB) // com.example.TicketNormal@7530d0a
}
