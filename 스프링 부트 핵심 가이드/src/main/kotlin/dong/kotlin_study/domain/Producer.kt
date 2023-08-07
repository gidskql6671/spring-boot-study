package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class Producer(
    val code: String,
    var name: String
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany
    val products: MutableList<Product> = arrayListOf()

    fun addProduct(product: Product) {
        products.add(product)
    }
}