package hello.core.member.repository

import hello.core.member.Member
import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository: MemberRepository {
    companion object {
        private var store = HashMap<Long, Member>()
    }

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long): Member = store[memberId]!!
}