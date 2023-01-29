package hello.core.member.repository

import hello.core.member.Member

interface MemberRepository {
    fun save(member: Member)

    fun findById(memberId: Long): Member
}