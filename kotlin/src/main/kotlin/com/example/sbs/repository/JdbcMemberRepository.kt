package com.example.sbs.repository

import com.example.sbs.domain.Member
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.DataSourceUtils.getConnection
import java.sql.*
import java.util.*
import javax.sql.DataSource


class JdbcMemberRepository(private val dataSource: DataSource): MemberRepository {
    override fun save(member: Member): Member {
        val sql = "insert into member(name) values(?)"
        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null
        return try {
            conn = getConnection()
            pstmt = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
            pstmt.setString(1, member.name)
            pstmt.executeUpdate()
            rs = pstmt.generatedKeys
            if (rs.next()) {
                member.id = rs.getLong(1)
            } else {
                throw SQLException("id 조회 실패")
            }
            member
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findById(id: Long): Optional<Member> {
        val sql = "select * from member where id = ?"
        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null
        return try {
            conn = getConnection()
            pstmt = conn.prepareStatement(sql)
            pstmt.setLong(1, id)
            rs = pstmt.executeQuery()
            if (rs.next()) {
                val member = Member(name = rs.getString("name"), id = rs.getLong("id"))
                Optional.of(member)
            } else {
                Optional.empty()
            }
        } catch (e: java.lang.Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findAll(): List<Member> {
        val sql = "select * from member"
        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null
        return try {
            conn = getConnection()
            pstmt = conn.prepareStatement(sql)
            rs = pstmt.executeQuery()
            val members: MutableList<Member> = ArrayList()
            while (rs.next()) {
                val member = Member(name = rs.getString("name"), id = rs.getLong("id"))
                members.add(member)
            }
            members
        } catch (e: java.lang.Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findByName(name: String): Optional<Member> {
        val sql = "select * from member where name = ?"
        var conn: Connection? = null
        var pstmt: PreparedStatement? = null
        var rs: ResultSet? = null
        return try {
            conn = getConnection()
            pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, name)
            rs = pstmt.executeQuery()
            if (rs.next()) {
                val member = Member(name = rs.getString("name"), id = rs.getLong("id"))
                return Optional.of(member)
            }
            Optional.empty()
        } catch (e: java.lang.Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    private fun getConnection(): Connection {
        return getConnection(dataSource)
    }

    private fun close(conn: Connection?, pstmt: PreparedStatement?, rs: ResultSet?) {
        try {
            rs?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        try {
            pstmt?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        try {
            conn?.let { close(it) }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    @Throws(SQLException::class)
    private fun close(conn: Connection) {
        DataSourceUtils.releaseConnection(conn, dataSource)
    }
}