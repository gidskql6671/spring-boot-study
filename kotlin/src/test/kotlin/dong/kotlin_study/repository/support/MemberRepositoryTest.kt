package dong.kotlin_study.repository.support

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest(
    @Autowired val memberRepository: MemberRepository
) {
    @Test
    fun findByNameTest() {
        val member = memberRepository.findByName("dong")

        member.ifPresent { println(it.name) }
    }

}