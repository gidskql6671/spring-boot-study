package com.example.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.location=" +
		"classpath:/application.yaml" +
		",classpath:/credential.yaml")
class SbsApplicationTests {

	@Test
	void contextLoads() {
	}

}
