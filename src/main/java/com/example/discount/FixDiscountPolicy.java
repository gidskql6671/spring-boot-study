package com.example.discount;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
	
	private final int discountFixAmount = 1000;
	
	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}
		
		return 0;
	}
}
