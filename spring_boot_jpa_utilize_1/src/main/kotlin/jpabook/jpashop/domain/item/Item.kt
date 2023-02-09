package jpabook.jpashop.domain.item

import jakarta.persistence.*
import jpabook.jpashop.common.AllOpen
import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@AllOpen
abstract class Item(
    var name: String? = null,
    var price: Int? = null,
    var stockQuantity: Int = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "item_id")
    val id: Long? = null

    @ManyToMany(mappedBy = "_items")
    private var _categories: MutableList<Category> = mutableListOf()
    val categories: List<Category>
        get() = _categories.toList()

    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity = restStock
    }
}