package dong.kotlin_study.repository.support

import dong.kotlin_study.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("memberRepositorySupport")
interface MemberRepository : JpaRepository<Member, Long>, MemberRepositoryCustom {
}