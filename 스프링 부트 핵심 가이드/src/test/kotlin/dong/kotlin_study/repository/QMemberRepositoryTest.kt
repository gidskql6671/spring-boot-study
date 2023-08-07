package dong.kotlin_study.repository

import com.querydsl.core.types.Predicate
import dong.kotlin_study.domain.Member
import dong.kotlin_study.domain.QMember
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.*


@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class QMemberRepositoryTest(
    @Autowired val qMemberRepository: QMemberRepository
) {

    @Test
    fun queryDSLTest() {
        val predicate: Predicate = QMember.member.name.containsIgnoreCase("dong")

        val foundMember: Optional<Member> = qMemberRepository.findOne(predicate)

        foundMember.ifPresent { println(it.name) }
    }
}