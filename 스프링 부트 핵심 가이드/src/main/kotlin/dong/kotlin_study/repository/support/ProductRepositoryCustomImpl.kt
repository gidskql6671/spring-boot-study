package dong.kotlin_study.repository.support

import dong.kotlin_study.domain.Member
import dong.kotlin_study.domain.QMember
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import java.util.*

class ProductRepositoryCustomImpl : QuerydslRepositorySupport(Member::class.java), MemberRepositoryCustom {
    override fun findByName(name: String): Optional<Member> {
        val member: QMember = QMember.member

        return Optional.ofNullable(from(member)
            .where(member.name.eq(name))
            .select(member)
            .fetchFirst())
    }
}