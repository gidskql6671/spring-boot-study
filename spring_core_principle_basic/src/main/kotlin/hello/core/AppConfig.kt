package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.RateDiscountPolicy
import hello.core.member.repository.MemberRepository
import hello.core.member.repository.MemoryMemberRepository
import hello.core.member.service.MemberService
import hello.core.member.service.MemberServiceImpl
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AppConfig {
    @Bean
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    @Bean
    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), discountPolicy())

    @Bean
    fun memberRepository(): MemberRepository = MemoryMemberRepository()

    @Bean
    fun discountPolicy(): DiscountPolicy = RateDiscountPolicy()
}