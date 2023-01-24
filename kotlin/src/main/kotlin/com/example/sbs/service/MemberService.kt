package com.example.sbs.service

import com.example.sbs.domain.Member
import com.example.sbs.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class MemberService(val memberRepository: MemberRepository) {
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicateMember(member: Member) {
        memberRepository.findByName(member.name)
            .ifPresent { throw IllegalStateException("이미 존재하는 회원입니다.") }
    }

    fun findMembers(): List<Member> = memberRepository.findAll()
    fun findOne(memberId: Long): Optional<Member> = memberRepository.findById(memberId)
}