package com.example.configuration;

import com.example.AppConfig;
import com.example.member.service.MemberService;
import com.example.member.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ctx.getBean("memberService", MemberService.class);

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 타입으로 조회")
	void findBeanByType() {
		MemberService memberService = ctx.getBean(MemberService.class);

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("존재하지 않는 빈 이름으로 조회")
	void findBeanByImplType() {
		assertThrows(NoSuchBeanDefinitionException.class, () -> ctx.getBean("xxxxx", MemberService.class));
	}
}
