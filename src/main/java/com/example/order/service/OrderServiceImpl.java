package com.example.order.service;

import com.example.discount.DiscountPolicy;
import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import com.example.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId).orElseThrow();
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
