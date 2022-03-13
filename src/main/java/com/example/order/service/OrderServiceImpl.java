package com.example.order.service;

import com.example.annotation.MainDiscountPolicy;
import com.example.discount.DiscountPolicy;
import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import com.example.order.domain.Order;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService, InitializingBean, DisposableBean {
	
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId).orElseThrow();
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("OrderServiceImpl.afterPropertiesSet");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("OrderServiceImpl.destroy");
	}

}
