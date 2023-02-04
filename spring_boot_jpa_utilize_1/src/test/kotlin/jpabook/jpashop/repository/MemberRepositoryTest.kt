package jpabook.jpashop.repository

import jpabook.jpashop.domain.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
internal class MemberRepositoryTest @Autowired constructor(val memberRepository: MemberRepository) {

    @Test
    @Transactional
    fun testMember() {
        // given
        val member = Member(name = "memberA")

        // when
        val savedId = memberRepository.save(member)
        val findMember = memberRepository.find(savedId)

        // then
        Assertions.assertThat(findMember.id).isEqualTo(findMember.id)
        Assertions.assertThat(findMember.name).isEqualTo(member.name)
    }
}