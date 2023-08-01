package dong.kotlin_study.domain

import jakarta.persistence.*

@Entity
class Category(
    @Column(unique = true) val code: String,
    var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    val products: List<Product> = arrayListOf()
}