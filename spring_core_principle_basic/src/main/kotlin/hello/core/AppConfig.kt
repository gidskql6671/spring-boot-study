package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.RateDiscountPolicy
import hello.core.member.repository.MemberRepository
import hello.core.member.repository.MemoryMemberRepository
import hello.core.member.service.MemberService
import hello.core.member.service.MemberServiceImpl
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl


class AppConfig {
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), discountPolicy())

    private fun memberRepository(): MemberRepository = MemoryMemberRepository()

    private fun discountPolicy(): DiscountPolicy = RateDiscountPolicy()
}