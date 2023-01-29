package hello.core.member.service

import hello.core.member.Member
import hello.core.member.repository.MemberRepository
import hello.core.member.repository.MemoryMemberRepository

class MemberServiceImpl: MemberService {

    private val memberRepository: MemberRepository = MemoryMemberRepository()

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member {
        return memberRepository.findById(memberId)
    }
}