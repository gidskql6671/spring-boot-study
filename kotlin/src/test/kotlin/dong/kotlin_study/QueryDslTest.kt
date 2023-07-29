package dong.kotlin_study

import com.querydsl.jpa.impl.JPAQueryFactory
import dong.kotlin_study.domain.QMember
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
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