package dong.kotlin_study.repository

import dong.kotlin_study.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface QMemberRepository: JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
}