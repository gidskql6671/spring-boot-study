package com.example.sbs.repository

import com.example.sbs.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByName(name: String): Optional<Member>
}