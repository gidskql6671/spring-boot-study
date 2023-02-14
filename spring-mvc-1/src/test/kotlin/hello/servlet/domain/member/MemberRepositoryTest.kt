package hello.servlet.domain.member

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test


internal class MemberRepositoryTest {
    var memberRepository = MemberRepository.getInstance()

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun save() {
        //given
        val member = Member("hello", 20)
        //when
        val savedMember = memberRepository.save(member)
        //then
        val findMember = memberRepository.findById(savedMember.id!!)
        assertThat(findMember).isEqualTo(savedMember)
    }

    @Test
    fun findAll() {
        //given
        val member1 = Member("member1", 20)
        val member2 = Member("member2", 30)
        memberRepository.save(member1)
        memberRepository.save(member2)
        //when
        val result = memberRepository.findAll()
        //then
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(member1, member2)
    }
}