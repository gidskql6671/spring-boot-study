package jpabook.jpashop.domain

import jakarta.persistence.*


@Entity
class Delivery(
    @Embedded var address: Address,

    @Enumerated(EnumType.STRING) var status: DeliveryStatus = DeliveryStatus.READY,

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    val id: Long? = null
}