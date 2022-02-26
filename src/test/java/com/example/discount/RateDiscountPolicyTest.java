package com.example.discount;

import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
	
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다.")
	void discountForVIP() {
		// given
		Member member = new Member(1L, "memberVIP", Grade.VIP);
		
		// when
		int discount = discountPolicy.discount(member, 10000);
		
		// then
		assertThat(discount).isEqualTo(1000);
	}
	
	@Test
	@DisplayName("BASIC은 할인이 적용되지 않는다.")
	void discountForBasic() {
		// given
		Member member = new Member(1L, "memberBasic", Grade.BASIC);

		// when
		int discount = discountPolicy.discount(member, 10000);

		// then
		assertThat(discount).isEqualTo(0);
	}
} 
