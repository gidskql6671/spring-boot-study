package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.service.MemberService
import hello.core.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    val acx = AnnotationConfigApplicationContext(AppConfig::class.java)

    val memberService = acx.getBean("memberService", MemberService::class.java)
    val orderService = acx.getBean("orderService", OrderService::class.java)

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)
    println("order = $order")
}