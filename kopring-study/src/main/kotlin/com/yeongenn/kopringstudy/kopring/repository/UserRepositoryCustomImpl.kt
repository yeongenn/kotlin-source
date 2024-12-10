package com.yeongenn.kopringstudy.kopring.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yeongenn.kopringstudy.kopring.domain.entity.QUser.user
import com.yeongenn.kopringstudy.kopring.domain.entity.QUserLoanHistory.userLoanHistory
import com.yeongenn.kopringstudy.kopring.domain.entity.User

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
): UserRepositoryCustom {

    // Querydsl : SQL과 거의 유사, 코드로 쿼리 작성함으로써 오타 발생 시 컴파일 단계에서 캐치
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user)    // select user
            .distinct() // select 결과에 DISTINCT 추가
            .from(user) // from user
            .leftJoin(userLoanHistory)  // left join user_loan_history
            .on(userLoanHistory.user.userId.eq(user.userId))    // on user_loan_history.user_id = user.user_id
            .fetchJoin()    // 앞의 join을 fetch join 으로 처리한다
            .fetch()    // 쿼리 실행하여 결과를 List로 가져온다
    }
    
    
}
/*
Querydsl - UserRepositoryCustom, UserRepositoryCustomImpl 방식
장점 : 서비스단에서 UserRepository  하나만 사용하면 된다
단점 : 인터페이스와 클래스를 항상 같이 만들어줘야 해서 번거롭다

 */