package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class Provider(var name: String) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "provider", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val productList: MutableList<Product> = arrayListOf()
}