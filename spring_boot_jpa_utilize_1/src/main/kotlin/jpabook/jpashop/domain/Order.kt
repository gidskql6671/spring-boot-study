package jpabook.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @ManyToOne @JoinColumn(name = "member_id") var member: Member,
    @OneToOne @JoinColumn(name = "delivery_id") var delivery: Delivery,
    var orderDate: LocalDateTime,
    @Enumerated(EnumType.STRING) var status: OrderStatus
) {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private val id: Long? = null

    @OneToMany(mappedBy = "order")
    private var _orderItems: MutableList<OrderItem> = mutableListOf()
    val orderItems: List<OrderItem>
        get() = _orderItems.toList()
}