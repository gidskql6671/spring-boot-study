package jpabook.jpashop.domain

import jakarta.persistence.*
import jpabook.jpashop.domain.item.Item

@Entity
class OrderItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    var orderPrice: Int,

    var count: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long? = null

    fun cancel() {
        item.addStock(count)
    }

    fun getTotalPrice(): Int = orderPrice * count
}

fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
    val orderItem = OrderItem(
        item = item,
        orderPrice = orderPrice,
        count = count
    )
    item.removeStock(count)

    return orderItem
}