package com.example

// Companion Object

// 자바의 static
// 정적인 변수나 정적인 메서드 선언할 때 사용

class Book private constructor(val id : Int, val title : String){   // private로 선언

    companion object BookFactory : IdProvider{  // 이름도 가능, 이름 따로 부여했으면 클래스명.Companion으로는 호출 X, 이름으로 부르거나 생략해야 한다

        override fun getId(): Int {
            return 111
        }

        // 프로퍼티도 작성 가능
        val myFavBook = "my favorite book"
        //fun create() = Book(0, "animal farm")

        fun create() = Book(getId(), myFavBook)
    }
}

// companion object는 인터페이스 상속도 가능
interface IdProvider {
    fun getId() : Int
}

fun main(){
    // companion object 주석 처리하고 아래 코드 입력
    //val book = Book()   // 에러 난다

    // 주석 풀고 아래 코드 입력
    //val book = Book.Companion.create()  // 이때 Companion은 생략 가능
    val book = Book.create()
    val bookId = Book.BookFactory.getId()   // 이름으로 호출하거나
    val bookId2 = Book.getId()  // 생략해야 한다

    println("${book.id}, ${book.title}")

    println(bookId == bookId2)
}
