package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import jakarta.persistence.criteria.*
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils


@Repository
class OrderRepository(
    @PersistenceContext private val em: EntityManager
) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order? = em.find(Order::class.java, id)

    fun findAll(orderSearch: OrderSearch): List<Order> {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Order> = cb.createQuery(Order::class.java)
        val o = cq.from(Order::class.java)
        val m: Join<Order, Member> = o.join("member", JoinType.INNER)

        val criteria: MutableList<Predicate> = ArrayList()
        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            val status = cb.equal(o.get<OrderStatus>("status"), orderSearch.orderStatus)
            criteria.add(status)
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            val name = cb.like(m.get("name"), "%" + orderSearch.memberName + "%")
            criteria.add(name)
        }

        cq.where(cb.and(*criteria.toTypedArray()));
        val query = em.createQuery(cq).setMaxResults(1000)

        return query.resultList
    }
}