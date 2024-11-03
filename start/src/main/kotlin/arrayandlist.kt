package com.example

// 5. Array and List

// Array
// 지정된 크기의 메모리가 이미 할당

// List - List, MutableList

fun array(){
    // array initialization - arrayOf
    val array = arrayOf(1, 2, 3)

    // list initialization - listOf
    val list = listOf(1, 2, 3)

    // 마찬가지로 타입 자동 추론
    val array2 = arrayOf(1, "a", 2, "b")
    val list2 = listOf(1, "d", 11L)

    // Array는 기본적으로 mutable - 크기 변경이 아닌 값 변경은 가능
    array[0] = 3
    // List - 읽기 전용, MutableList는 읽기, 변경 모두 가능
    var result = list.get(0)

    //var arrayList = arrayListOf<Int>()
    val arrayList = arrayListOf<Int>() // 에러 X
    // val은 값 변경이 안된다 며?
    // -> arrayList가 참조하는 값 자체는 변하지 않잖아?!
    //arrayList = arrayListOf<Int>()  // 새로운 참조값 할당이 불가한 것
    
    arrayList.add(10)
    arrayList.add(20)
    arrayList[0] = 100
}