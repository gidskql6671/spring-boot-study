package jpabook.jpashop.domain

import jakarta.persistence.*


@Entity
class Delivery(
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order,

    @Embedded var address: Address,
    @Enumerated(EnumType.STRING) var status: DeliveryStatus
) {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private val id: Long? = null
}