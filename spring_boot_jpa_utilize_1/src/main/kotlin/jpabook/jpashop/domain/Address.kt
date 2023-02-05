package jpabook.jpashop.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    @Column(name = "city") val city: String,
    @Column(name = "street") val street: String,
    @Column(name = "zipcode") val zipcode: String
)