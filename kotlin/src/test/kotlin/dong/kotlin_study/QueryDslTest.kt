package dong.kotlin_study

import com.querydsl.jpa.impl.JPAQueryFactory
import dong.kotlin_study.domain.QMember
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class QueryDslTest(
    @Autowired val jpaQueryFactory: JPAQueryFactory
) {

    @Test
    fun queryDslTest() {
        val qMember = QMember.member

        val memberList: List<String> = jpaQueryFactory
            .select(qMember.name)
            .from(qMember)
            .where(qMember.name.eq("dong"))
            .fetch()

        memberList.forEach { println(it) }
    }
}