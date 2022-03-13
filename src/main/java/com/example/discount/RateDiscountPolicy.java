package com.example.discount;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("secondaryDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {
	
	private final int discountPercent = 10;


	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		}
		else {
			return 0;
		}
	}
}
