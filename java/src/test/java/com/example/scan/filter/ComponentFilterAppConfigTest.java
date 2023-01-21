package com.example.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
	
	@Test
	void filterScan() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		
		BeanA beanA = ctx.getBean(BeanA.class);
		assertThat(beanA).isInstanceOf(BeanA.class);
		
		Assertions.assertThrows(NoSuchBeanDefinitionException.class,
				() -> ctx.getBean(BeanB.class));
	}
	
	@Configuration
	@ComponentScan(
			includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
			excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig {
		
	}
}
