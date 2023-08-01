package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class Provider(var name: String) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    val productList: MutableList<Product> = arrayListOf()
}