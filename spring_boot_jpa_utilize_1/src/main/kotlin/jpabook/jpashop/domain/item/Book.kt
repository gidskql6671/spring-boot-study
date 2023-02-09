package jpabook.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String? = null,
    price: Int? = null,
    stockQuantity: Int = 0,
    var isbn: String? = null,
    var author: String? = null
): Item(name, price, stockQuantity)