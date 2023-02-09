package jpabook.jpashop.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @Column(name = "city") val city: String? = null,
    @Column(name = "street") val street: String? = null,
    @Column(name = "zipcode") val zipcode: String? = null
)