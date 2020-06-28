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

}
