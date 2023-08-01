package dong.kotlin_study.repository

import dong.kotlin_study.domain.Member
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
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