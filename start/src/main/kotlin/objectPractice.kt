package com.example

object VehicleFactory {
    val vehicles = mutableListOf<Vehicle>()
    fun makeVehicle(horsePower : Int) : Vehicle {
        val vehicle = Vehicle(horsePower)
        vehicles.add(vehicle)
        return vehicle
    }
}

// object가 다른 class들과 다른 점
// 전체 앱이 실행될 때 VehicleFactory는 딱 한번 만들어지는 객체 -> singleton pattern
// 메모리가 불필요하게 사용되는 것을 막을 수 있다

data class Vehicle(val horsePower : Int)

fun main(){
    val car = VehicleFactory.makeVehicle(10)    // VehicleFactory 객체 생성없이 바로 makeVehicle 메서드 호출
    val car2 = VehicleFactory.makeVehicle(20)

    println(car)    // Vehicle(horsePower=10)
    println(car2)   // Vehicle(horsePower=20)
    println(VehicleFactory.vehicles.size.toString())    // 2
}