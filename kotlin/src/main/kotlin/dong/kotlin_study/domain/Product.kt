package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class Product(
    var name: String,
    var price: Int,
    var stock: Int
) : BaseEntity()  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(mappedBy = "product")
    var productDetail: ProductDetail? = null
}