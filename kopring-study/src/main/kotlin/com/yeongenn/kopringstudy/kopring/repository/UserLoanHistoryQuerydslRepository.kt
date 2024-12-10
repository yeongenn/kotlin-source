package com.yeongenn.kopringstudy.kopring.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yeongenn.kopringstudy.kopring.domain.entity.QUserLoanHistory.userLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.UserLoanStatus
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository (
    private val queryFactory: JPAQueryFactory,
) {

    // findByBookTitle
    fun find(bookTitle: String): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookTitle.eq(bookTitle)
            )
            .limit(1)
            .fetchOne() // List<Entity>가 아니라 Entity 하나만 결과로 반환

    }

    /*
    findByBookTitleAndStatus
    파라미터로 status 추가 - default로 null을 넣었기 때문에 외부에서는 bookTitle만 사용할 수도 있고, status까지 같이 사용할 수도 있다
    ?.let 활용해 status가 null인 경우에는 where 조건에 user_loan_history.status = status가 들어가지 않도록 설정
    where에 들어오는 조건이 null인 경우 Querydsl은 이를 무시한다
    또한 where절에 여러 조건이 들어오면 각 조건이 AND로 결합한다
    -> status가 null이 아닌 경우에만 book_title = ? and status = ? 가 실행
     */
    fun find(bookTitle: String, status: UserLoanStatus? = null): UserLoanHistory? { 
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookTitle.eq(bookTitle),
                status?.let { userLoanHistory.status.eq(status) }
            )
            .limit(1)
            .fetchOne()
    }

    /*
    countByStatus

     */
    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.count()) // count(id)로 변경된다
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            .fetchOne() ?: 0L   // count의 결과는 숫자 1개이므로 fetchOne(0) 사용, 혹시나 결과가 비어있다면 elvis 연산자 사용해 0L 반환하도록
    }
}