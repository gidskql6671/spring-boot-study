package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.springframework.stereotype.Component

@Component
class RateDiscountPolicy: DiscountPolicy {
    private val discountPercent = 10

    override fun discount(member: Member, price: Int): Int =
        if (member.grade == Grade.VIP) price * discountPercent / 100 else 0
}