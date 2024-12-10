package com.yeongenn.kopringstudy.kopring.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yeongenn.kopringstudy.kopring.domain.dto.BookStatResponse
import org.springframework.expression.spel.ast.Projection
import org.springframework.stereotype.Component
import com.yeongenn.kopringstudy.kopring.domain.entity.QBook.book

@Component  // JPAQueryFactory 주입받을 수 있도록 @Component 혹은 @Repository
class BookQuerydslRepository (
    private val queryFactory: JPAQueryFactory
) {

    fun getStats(): List<BookStatResponse> {
        return queryFactory
            .select(
                Projections.constructor(    // 주어진 DTO의 생성자를 호출
                    BookStatResponse::class.java,   // 총 3개 파라미터
                    book.type,
                    book.id.count(),
                )
            )
            .from(book) // 여기까지 SQL문으로 보면 select book.type, count(book_id) from book
            .groupBy(book.type)
            .fetch()
    }
}

/*
Querydsl - BookQuerydslRepository 방식
장점 : 클래스만 바로 만들면 되서 간결
단점 : 서비스단에서 필요에 따라 두 Repository 모두 사용해줘야 한다

강사는 이 방법 선호한다
멀티 모듈 사용할 경우, 모듈별로 각기 다른 Repository를 사용하는 경우가 많아 단점이 상쇄되고 장점이 극대화된단다
 */