package com.yeongenn.kopringstudy.kopring.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuerydslConfig(
    private val em: EntityManager,
) {
    @Bean   // JPAQueryFactory를 스프링 빈으로 등록
    fun querydsl(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}
