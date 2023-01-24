package com.example.sbs.repository

import com.example.sbs.domain.Member
import java.util.*

class MemoryMemberRepository: MemberRepository {
    companion object {
        private var store = HashMap<Long, Member>()
        private var sequence = 0L
    }

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long): Optional<Member> = Optional.ofNullable(store[id])

    override fun findByName(name: String): Optional<Member> {
        return store.values.stream()
            .filter { it.name == name }
            .findAny()
    }

    override fun findAll(): List<Member> = store.values.toList()
}