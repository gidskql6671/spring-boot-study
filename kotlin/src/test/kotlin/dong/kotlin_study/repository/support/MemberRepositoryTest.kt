package dong.kotlin_study.repository.support

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class MemberRepositoryTest(
    @Autowired val memberRepository: MemberRepository
) {
    @Test
    fun findByNameTest() {
        val member = memberRepository.findByName("dong")

        member.ifPresent { println(it.name) }
    }

}