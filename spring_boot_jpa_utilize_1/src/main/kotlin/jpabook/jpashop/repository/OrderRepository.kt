package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(
    @PersistenceContext private val em: EntityManager
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order? = em.find(Order::class.java, id)
}