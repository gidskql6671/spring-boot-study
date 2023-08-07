package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

// jpa & spring에 대한 공부를 위한 테스트라서 유닛 테스트가 아닌 통합 테스트로 진행
@SpringBootTest
@Transactional
internal class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "kim")

        // when
        val savedId = memberService.join(member)
        val findMember = memberRepository.findOne(savedId)

        // then
        Assertions.assertThat(findMember).isEqualTo(member)
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "kim")
        val member2 = Member(name = "kim")

        // when & then
        memberService.join(member1)

        assertThrows<IllegalStateException> {
            memberService.join(member2)
        }
    }
}