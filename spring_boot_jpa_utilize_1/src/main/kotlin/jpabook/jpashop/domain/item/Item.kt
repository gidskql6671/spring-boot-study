package jpabook.jpashop.domain.item

import jakarta.persistence.*
import jpabook.jpashop.common.AllOpen
import jpabook.jpashop.domain.Category

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@AllOpen
abstract class Item(
    var name: String,
    var price: Int,
    var stockQuantity: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "item_id")
    val id: Long? = null

    @ManyToMany(mappedBy = "_items")
    private var _categories: MutableList<Category> = mutableListOf()
    val categories: List<Category>
        get() = _categories.toList()
}