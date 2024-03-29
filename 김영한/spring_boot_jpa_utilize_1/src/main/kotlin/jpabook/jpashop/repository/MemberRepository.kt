package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jpabook.jpashop.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
    @PersistenceContext private val em: EntityManager
) {

    fun save(member: Member) {
        em.persist(member)
    }

    fun findOne(id: Long): Member? = em.find(Member::class.java, id)

    fun findAll(): List<Member> = em.createQuery("select m from Member m", Member::class.java).resultList

    fun findByName(name: String): List<Member> {
        return em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }
}