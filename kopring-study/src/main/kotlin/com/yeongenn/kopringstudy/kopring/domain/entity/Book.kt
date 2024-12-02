package com.yeongenn.kopringstudy.kopring.domain.entity

import jakarta.persistence.*

@Entity
class Book(
    val title: String,

    //val type: String, // 책 분야 추가
    @Enumerated(EnumType.STRING) // Enum에서 지정한 문자열 넣기 위해서 추가
    val type: BookType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    init {
        if (title.isBlank()) {
            throw IllegalArgumentException("title cannot be empty.")
        }
    }

    /*
    Book 객체 수정 
    -> 테스트 코드에서도 생성자 사용하고 있기 때문에 테스트 코드도 수정해야
    
    * 이때 생각할 점
    - 프로덕션 코드에 변경이 생길 때마다 테스트 코드도 수정해야 한다면 유지보수 비용 증가
    - 규모가 커질 때마다 감당할 수 있는 수준 넘어설 수도
    
    - 새로 추가한 type의 경우 대출, 반납 기능에는 크리티컬하지 않는다
      -> type에 어떤 값이 들어가도 테스트의 성공, 실패에는 영향이 X
    
    - 객체에서 필드가 하나 추가되더라도 객체 사용하는 테스트 코드에 영향이 가지 않도록 하고 싶다
      -> Book 객체 만드는 함수를 미리 생성해놓기
      -> companion object 만들어 정적 팩토리 메서드 생성, 이때 default parameter 활용해보자
      
      (companion object는 kotlin convention 상 클래스 마지막 부분에 위치
      
    * Object Mother - 테스트에 이용할 객체 만드는 함수
    * Test Fixture - 위 과정(?)을 통해서 생겨난 테스트용 객체

    (DTO의 경우 entity 보다는 사용 빈도가 적을테니 선택적으로 만들어도 될 것)
     */
    companion object {
        fun fixture(
            title: String = "Alice in wonderland",
            //type: String = "LITERATURE",
            type: BookType = BookType.LITERATURE,
            id: Long? = null
        ): Book {
            return Book(
                title = title,
                type = type,
                id = id
            )
        }
    }
}

// 책 분류 enum class 만들기
// DB에는 enum 필드 인덱스값이 들어가기 떄문에 문자열을 넣고 싶다면 @Enumerated 사용
enum class BookType{
    COMPUTER,
    ECONOMY,
    SOCIETY,
    LANGUAGE,
    LITERATURE,
    ;
}