package jpabook.jpashop.domain

import jakarta.persistence.*
import jpabook.jpashop.domain.item.Item

@Entity
class OrderItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,

    var count: Int
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long? = null
}