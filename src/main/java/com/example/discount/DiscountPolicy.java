package com.example.discount;

import com.example.member.domain.Member;

public interface DiscountPolicy {
	int discount(Member member, int price);
}
