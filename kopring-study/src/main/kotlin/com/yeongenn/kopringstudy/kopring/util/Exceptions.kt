package com.yeongenn.kopringstudy.kopring.util

class Exceptions {


}

fun fail(): Nothing { // Nothing 타입 - 이 함수는 항상 정상적으로 종료되지 않는다는 의미
    throw IllegalArgumentException()
}