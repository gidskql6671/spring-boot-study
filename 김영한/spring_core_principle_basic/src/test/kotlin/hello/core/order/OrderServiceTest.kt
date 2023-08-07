package hello.core.order

import hello.core.AppConfig
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.service.MemberService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OrderServiceTest {

    private lateinit var memberService: MemberService
    private lateinit var orderService: OrderService

    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()

        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)

        Assertions.assertThat(order.discountPrice).isEqualTo(1000)

    }
}