package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.service.MemberServiceImpl


fun main(args: Array<String>) {
    val memberService = MemberServiceImpl()
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)
    
    val findMember = memberService.findMember(1L)
    println("member.name = ${member.name}")
    println("findMember.name = ${findMember.name}")
}