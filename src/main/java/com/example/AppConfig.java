package com.example;

import com.example.discount.DiscountPolicy;
import com.example.discount.RateDiscountPolicy;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.MemoryMemberRepository;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import com.example.order.service.OrderService;
import com.example.order.service.OrderServiceImpl;

public class AppConfig {
	
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}
	
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
	
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}
}
