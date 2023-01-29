package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member

class FixDiscountPolicy: DiscountPolicy {
    private val discountFixAmount = 1000

    override fun discount(member: Member, price: Int): Int =
        if (member.grade == Grade.VIP) discountFixAmount else 0

}