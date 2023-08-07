package dong.kotlin_study.repository.support

import dong.kotlin_study.domain.Member
import java.util.*

interface MemberRepositoryCustom {
    fun findByName(name: String): Optional<Member>
}