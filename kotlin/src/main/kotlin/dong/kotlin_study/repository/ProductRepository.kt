package dong.kotlin_study.repository

import dong.kotlin_study.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
    fun findByName(name: String) : List<Product>
}