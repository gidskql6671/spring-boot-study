package hello.core.member.service

import hello.core.member.Member
import hello.core.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member {
        return memberRepository.findById(memberId)
    }
}