package dong.kotlin_study.repository

import dong.kotlin_study.domain.Member
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class MemberRepositoryTest (
    @Autowired val memberRepository: MemberRepository
) {
    @Test
    fun auditingTest() {
        val member = Member(name = "dong")

        val savedMember = memberRepository.save(member)

        println("savedMember.createdAt = ${savedMember.createdAt}")
        println("savedMember.updatedAt = ${savedMember.updatedAt}")
    }
}