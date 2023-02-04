package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jpabook.jpashop.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
    @PersistenceContext
    private val em: EntityManager
) {

    fun save(member: Member): Long {
        em.persist(member)

        return member.id!!
    }

    fun find(id: Long): Member = em.find(Member::class.java, id)
}