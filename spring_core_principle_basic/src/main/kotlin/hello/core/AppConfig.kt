package hello.core

import hello.core.discount.FixDiscountPolicy
import hello.core.member.repository.MemoryMemberRepository
import hello.core.member.service.MemberService
import hello.core.member.service.MemberServiceImpl
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl


class AppConfig {
    fun memberService(): MemberService {
        return MemberServiceImpl(MemoryMemberRepository())
    }

    fun orderService(): OrderService {
        return OrderServiceImpl(
            MemoryMemberRepository(),
            FixDiscountPolicy()
        )
    }
}