package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.service.MemberService
import org.springframework.context.annotation.AnnotationConfigApplicationContext


fun main(args: Array<String>) {
    val acx = AnnotationConfigApplicationContext(AppConfig::class.java)

    val memberService = acx.getBean("memberService", MemberService::class.java)
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)
    
    val findMember = memberService.findMember(1L)
    println("member.name = ${member.name}")
    println("findMember.name = ${findMember.name}")
}