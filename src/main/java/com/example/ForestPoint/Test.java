package com.example.ForestPoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forestpoint")
public class Test {

	private final ForestPoint fp;

	public Test(ForestPoint fp) {
		this.fp = fp;
	}
	
	@GetMapping("/test")
	public String wsest() {
		return fp.getResponse().toString();
	}
}
