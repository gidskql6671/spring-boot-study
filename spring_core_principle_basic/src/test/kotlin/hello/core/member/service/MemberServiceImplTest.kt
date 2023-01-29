package hello.core.member.service

import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MemberServiceImplTest {

    private val memberService: MemberService = MemberServiceImpl()

    @Test
    fun join() {
        val member = Member(1L, "memberA", Grade.VIP)

        memberService.join(member)
        val findMember = memberService.findMember(1L)

        Assertions.assertThat(findMember).isEqualTo(member)
    }
}