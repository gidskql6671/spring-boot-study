package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class ProductDetail(
    var description: String,
    @OneToOne
    @JoinColumn(name = "product_id")
    val product: Product
) : BaseEntity()  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}