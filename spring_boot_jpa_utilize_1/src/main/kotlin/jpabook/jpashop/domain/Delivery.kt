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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    val id: Long? = null
}