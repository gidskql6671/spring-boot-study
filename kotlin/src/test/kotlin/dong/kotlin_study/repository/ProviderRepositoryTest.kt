package dong.kotlin_study.repository

import dong.kotlin_study.domain.Product
import dong.kotlin_study.domain.Provider
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class ProviderRepositoryTest(
    @Autowired val providerRepository: ProviderRepository,
    @Autowired val productRepository: ProductRepository
) {

    @Test
    fun cascadeTest() {
        val provider = savedProvider("공급업체")

        val product1 = savedProduct("상품1", 1000, 1000)
        val product2 = savedProduct("상품2", 1500, 100)
        val product3 = savedProduct("상품3", 2000, 500)

        product1.provider = provider
        product2.provider = provider
        product3.provider = provider

        provider.productList.addAll(arrayListOf(product1, product2, product3))

        providerRepository.save(provider)
    }

    @Test
    fun orphanRemovalTest() {
        val provider = savedProvider("공급업체")

        val product1 = savedProduct("상품1", 1000, 1000)
        val product2 = savedProduct("상품2", 1500, 100)
        val product3 = savedProduct("상품3", 2000, 500)

        product1.provider = provider
        product2.provider = provider
        product3.provider = provider

        provider.productList.addAll(arrayListOf(product1, product2, product3))

        providerRepository.saveAndFlush(provider)

        providerRepository.findAll().forEach{ println(it.name) }
        productRepository.findAll().forEach{ println(it.name) }

        val foundProduct = providerRepository.findAll().first()
        foundProduct.productList.removeAt(0)

        providerRepository.findAll().forEach{ println(it.name) }
        productRepository.findAll().forEach{ println(it.name) }
    }

    private fun savedProvider(name: String) = Provider(name = name)
    private fun savedProduct(name: String, price: Int, stock: Int) =
        Product(name = name, price = price, stock = stock)
}