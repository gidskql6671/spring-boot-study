package com.example.forestPoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forestpoint")
public class Test {

	private final ForestPointAPI fp;

	public Test(ForestPointAPI fp) {
		this.fp = fp;
	}
	
	@GetMapping("/test")
	public TestResponse wsest() {
		return new TestResponse(fp.getResponse());
	}
}
