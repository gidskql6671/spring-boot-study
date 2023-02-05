package jpabook.jpashop.domain

import jakarta.persistence.*
import jpabook.jpashop.domain.item.Item

@Entity
class Category(
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category?
) {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private val id: Long? = null

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    private var _items: MutableList<Item> = mutableListOf()
    val items: List<Item>
        get() = _items.toList()

    @OneToMany
    private var _children: MutableList<Category> = mutableListOf()
    val children: List<Category>
        get() = _children.toList()

    fun addChildCategory(child: Category) {
        this._children += child
        child.parent = this
    }
}