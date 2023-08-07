package dong.kotlin_study.repository

import dong.kotlin_study.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByName(name: String): Optional<Member>
}