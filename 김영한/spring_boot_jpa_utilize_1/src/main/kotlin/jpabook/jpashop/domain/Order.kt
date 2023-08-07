package jpabook.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) {

    init {
        member.addOrder(this)
        delivery.order = this
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    private var _orderItems: MutableList<OrderItem> = mutableListOf()
    val orderItems: List<OrderItem>
        get() = _orderItems.toList()

    fun addOrderItem(orderItem: OrderItem) {
        _orderItems += orderItem
        orderItem.order = this
    }

    fun setMemberRelation(member: Member) {
        this.member = member
        member.addOrder(this)
    }

    fun setDeliveryRelation(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw java.lang.IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        _orderItems.forEach { it.cancel() }
    }

    fun getTotalPrice(): Int = orderItems.sumOf { it.getTotalPrice() }
}

fun createOrder(member: Member, delivery: Delivery, vararg orderItem: OrderItem): Order {
    val order = Order(
        member = member,
        delivery = delivery,
        orderDate = LocalDateTime.now(),
        status = OrderStatus.ORDER
    ).also {
        orderItem.forEach { orderItem -> it.addOrderItem(orderItem) }
    }

    return order
}