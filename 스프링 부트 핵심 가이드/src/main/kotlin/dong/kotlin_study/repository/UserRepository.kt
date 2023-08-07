package dong.kotlin_study.repository

import dong.kotlin_study.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun getByUid(uid: String): User?
}