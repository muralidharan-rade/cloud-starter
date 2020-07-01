package com.nativejava.sample.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {

	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "/test")
	public String testRestful() {
		return "hello guys";
	}

	@GetMapping(path = "/test-json")
	public SampleBean testRestfulBean() {
		return new SampleBean("hello guys");
	}

	@GetMapping(path = "/test-json/{name}")
	public SampleBean testRestfulBeanPathVariable(@PathVariable String name) {
		return new SampleBean("Hello Dear " + name);
	}

	@GetMapping(path = "/test-message")
	// public String testInternalisation(@RequestHeader(name="Accept-Language",
	// required = false) Locale locale) {
	public String testInternalisation() {
		String message = messageSource.getMessage("test.message", null, LocaleContextHolder.getLocale());
		return message;
	}

	@GetMapping(path = "/test/version", params = "v=1")
	public String testVersioningParamV1() {
		return "hello guys v1";
	}

	@GetMapping(path = "/test/version", params = "v=2")
	public String testVersioningParamV2() {
		return "hello guys v2";
	}
	
	@GetMapping(path = "/test/versionhead", headers = "accept-version=v1")
	public String testVersioningHeaderV1() {
		return "hello guys - header v1";
	}
	
	@GetMapping(path = "/test/versionhead", headers = "accept-version=v2")
	public String testVersioningHeaderV2() {
		return "hello guys - header v2";
	}

}
