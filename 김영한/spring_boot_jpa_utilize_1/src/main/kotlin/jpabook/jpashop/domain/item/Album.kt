package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    var artist: String,
    var etc: String
) : Item(name, price, stockQuantity)