package jpabook.jpashop.domain

import jakarta.persistence.*
import sun.jvm.hotspot.oops.CellTypeState.value
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
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private val id: Long? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    private var _orderItems: MutableList<OrderItem> = mutableListOf()
    val orderItems: List<OrderItem>
        get() = _orderItems.toList()

    fun addOrderItem(orderItem: OrderItem) {
        _orderItems += orderItem
        orderItem.order = this
    }

    fun setMember(member: Member) {
        this.member = member
        member.addOrder(this)
    }

    fun setDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }
}