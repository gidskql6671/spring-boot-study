package com.example.sbs.repository

import com.example.sbs.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JdbcTemplateMemberRepository(private val jdbcTemplate: JdbcTemplate): MemberRepository {
    private val mapper = RowMapper<Member> { rs, _ ->
        Member(
            id = rs.getLong("id"),
            name = rs.getString("name")
        )
    }

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters: MutableMap<String, Any?> = HashMap()
        parameters["name"] = member.name

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()

        return member
    }

    override fun findById(id: Long): Optional<Member> {
        val result = jdbcTemplate.query("select * from member where id = ?", mapper, id)

        return result.stream().findAny()
    }

    override fun findByName(name: String): Optional<Member> {
        val result = jdbcTemplate.query("select * from member where name = ?", mapper, name)

        return result.stream().findAny()
    }

    override fun findAll(): List<Member> = jdbcTemplate.query("select * from member", mapper)
}