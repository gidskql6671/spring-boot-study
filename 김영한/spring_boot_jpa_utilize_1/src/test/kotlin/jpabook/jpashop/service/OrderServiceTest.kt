package jpabook.jpashop.service

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.item.Book
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@Transactional
internal class OrderServiceTest @Autowired constructor(
    private val em: EntityManager,
    private val orderService: OrderService,
    private val orderRepository: OrderRepository
) {

    @Test
    fun 상품주문() {
        // given
        val member = createMember()
        val item = createBook(name = "시골 JPA", price = 10000, stockQuantity = 10)
        val orderCount = 2

        // when
        val orderId = orderService.order(member.id!!, item.id!!, orderCount)

        // then
        val getOrder = orderRepository.findOne(orderId)!!

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.status, "상품 주문시 상태는 ORDER")
        Assertions.assertEquals(1, getOrder.orderItems.size, "주문한 상품 종류 수가 정확해야 한다.")
        Assertions.assertEquals(10000 * 2, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.")
        Assertions.assertEquals(8, item.stockQuantity, "주문 수량만큼 재고가 줄어야 한다.")
    }

    @Test
    fun 상품수량_제한초과() {
        // given
        val member = createMember()
        val item = createBook("시골 JPA2", 1000, 9) //이름, 가격, 재고

        val orderCount = 10 //재고보다 많은 수량

        // when & then
        assertThrows<NotEnoughStockException> {
            orderService.order(member.id!!, item.id!!, orderCount)
        }
    }

    @Test
    fun 주문취소() {
        // given
        val member = createMember()
        val item = createBook("시골 JPA", 10000, 10) //이름, 가격, 재고

        val orderCount = 2

        val orderId = orderService.order(member.id!!, item.id!!, orderCount)

        // when
        orderService.cancel(orderId)

        // then
        val getOrder = orderRepository.findOne(orderId)!!
        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.status, "주문 취소시 상태는 CANCEL 이다.")
        Assertions.assertEquals(10, item.stockQuantity, "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.")
    }

    private fun createMember(): Member {
        val member = Member(
            name = "회원1",
            address = Address(city = "c1", street = "s1", zipcode = "123-123")
        )
        em.persist(member)
        return member
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book(name = name, price = price, stockQuantity = stockQuantity)
        em.persist(book)

        return book
    }
}