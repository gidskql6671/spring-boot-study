package com.example.sbs.service

import com.example.sbs.domain.Member
import com.example.sbs.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberServiceIntgTest {

    @Autowired lateinit var memberService: MemberService
    @Autowired lateinit var memberRepository: MemberRepository

    @Test
    fun 회원가입() {
        val member = Member(name = "hello")

        val saveId = memberService.join(member)

        val findMember: Member = memberRepository.findById(saveId).get()
        assertEquals(member.name, findMember.name)
    }

    @Test
    fun 중복_회원_예외() {
        val member1 = Member(name = "spring")
        val member2 = Member(name = "spring")

        memberService.join(member1)

        val e = assertThrows(IllegalStateException::class.java) { memberService.join(member2) }
        assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }
}