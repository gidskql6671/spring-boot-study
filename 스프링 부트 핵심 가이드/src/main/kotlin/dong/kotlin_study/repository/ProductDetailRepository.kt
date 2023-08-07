package dong.kotlin_study.repository

import dong.kotlin_study.domain.ProductDetail
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDetailRepository: JpaRepository<ProductDetail, Long> {
}