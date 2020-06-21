package com.nativejava.restful;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {

	@GetMapping(path = "/test")
	public String testRestful() {
		return "hello guys";
	}
	
	@GetMapping(path = "/test-json")
	public SampleBean testRestfulBean() {
		return new SampleBean("hello guys");
	}

}
