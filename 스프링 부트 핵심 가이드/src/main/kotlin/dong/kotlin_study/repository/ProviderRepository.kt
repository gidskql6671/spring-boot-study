package dong.kotlin_study.repository

import dong.kotlin_study.domain.Provider
import org.springframework.data.jpa.repository.JpaRepository

interface ProviderRepository: JpaRepository<Provider, Long>