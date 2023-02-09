package jpabook.jpashop.service

import jpabook.jpashop.domain.Delivery
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.createOrder
import jpabook.jpashop.domain.createOrderItem
import jpabook.jpashop.repository.ItemRepository
import jpabook.jpashop.repository.MemberRepository
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        val member = memberRepository.findOne(memberId)!!
        val item = itemRepository.findOne(itemId)!!

        val delivery = Delivery(address = member.address!!)
        val orderItem = createOrderItem(item, item.price!!, count)
        val order = createOrder(member, delivery, orderItem)

        orderRepository.save(order)

        return order.id!!
    }

    @Transactional
    fun cancel(orderId: Long) {
        val order = orderRepository.findOne(orderId)

        order?.cancel()
    }

    fun findOrders(orderSearch: OrderSearch): List<Order> = orderRepository.findAll(orderSearch)
}