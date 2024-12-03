package com.yeongenn.kopringstudy.kopring.domain.dto

data class UserLoanHistoryResponse(
    val name: String,
    val books: List<BookHistoryResponse>
)

// kotlin에서는 한 파일에 여러 클래스 만드는 것 가능
// 클래스들이 비슷한 특성을 가지고 있다면 권장되는 경우도 O
data class BookHistoryResponse (
    val title: String,
    val isReturn: Boolean
)
