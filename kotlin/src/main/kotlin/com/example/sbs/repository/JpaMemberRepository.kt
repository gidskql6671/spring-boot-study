package com.example.sbs.repository

import com.example.sbs.domain.Member
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JpaMemberRepository(private val em: EntityManager): MemberRepository {
    override fun save(member: Member): Member {
        em.persist(member)

        return member
    }

    override fun findById(id: Long): Optional<Member> {
        val member = em.find(Member::class.java, id)
        return Optional.ofNullable(member)
    }

    override fun findByName(name: String): Optional<Member> {
        return em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
            .stream()
            .findAny()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

}