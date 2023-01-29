package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.member.repository.MemberRepository
import hello.core.member.repository.MemoryMemberRepository

class OrderServiceImpl: OrderService {
    private val memberRepository: MemberRepository = MemoryMemberRepository()
    private val discountPolicy: DiscountPolicy = FixDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)

        return Order(memberId, itemName, itemPrice, discountPrice)
    }

}