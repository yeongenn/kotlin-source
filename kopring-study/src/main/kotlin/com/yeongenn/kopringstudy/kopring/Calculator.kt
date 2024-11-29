package com.charm.wonderlandshop.kopring

class Calculator(
    //private var _number: Int, // backing property : 각 메서드 본문도 맞게 수정해줘야한다
    var number: Int,
) {

    // backing property
//    val number: Int
//        get() = this._number

    fun add(operand: Int) {
        this.number += operand
    }

    fun minus(operand: Int) {
        this.number -= operand
    }

    fun multiply(operand: Int) {
        this.number *= operand
    }

    fun divide(operand: Int) {
        if(operand == 0){
            throw IllegalArgumentException("cannot divide by 0")
        }
        this.number /= operand
    }
}