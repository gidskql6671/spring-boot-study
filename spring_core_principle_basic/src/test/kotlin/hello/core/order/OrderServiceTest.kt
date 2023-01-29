package hello.core.order

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.service.MemberService
import hello.core.member.service.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class OrderServiceTest {

    private val memberService: MemberService = MemberServiceImpl()
    private val orderService: OrderService = OrderServiceImpl()

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)

        Assertions.assertThat(order.discountPrice).isEqualTo(1000)

    }
}