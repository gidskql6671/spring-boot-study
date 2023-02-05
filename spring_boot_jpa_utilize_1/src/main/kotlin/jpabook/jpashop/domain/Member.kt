package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class Member (
    var name: String,
    @Embedded var address: Address
) {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    val id: Long? = null

    @OneToMany(mappedBy = "member")
    private var _orders: MutableList<Order> = mutableListOf()
    val orders: List<Order>
        get() = _orders.toList()

    fun addOrder(order: Order) {
        _orders += order
    }
}