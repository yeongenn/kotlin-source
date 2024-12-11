package com.yeongenn.kopringstudy.kopring.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.yeongenn.kopringstudy.kopring.domain.entity.User
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus

data class UserLoanHistoryResponse(
    val name: String, // 유저 이름
    val books: List<BookHistoryResponse>
) {
    // Service 본래 목적에 충실하기 위해 데이터를 DTO에 맵핑하는 역할을 DTO로
    // 정적 팩토리 메서드 활용
    companion object {
        fun of(user: User): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map { history ->
                    BookHistoryResponse.of(history)
                }
            )
        }
    }
}

// kotlin에서는 한 파일에 여러 클래스 만드는 것 가능
// 클래스들이 비슷한 특성을 가지고 있다면 권장되는 경우도 O
data class BookHistoryResponse(
    val title: String,

    @get:JsonProperty("isReturn")
    val isReturn: Boolean
) {
    companion object {
        fun of(userLoanHistory: UserLoanHistory): BookHistoryResponse {
            return BookHistoryResponse(
                title = userLoanHistory.bookTitle,
                isReturn = userLoanHistory.status == UserLoanStatus.RETURNED
            )
        }
    }
}
