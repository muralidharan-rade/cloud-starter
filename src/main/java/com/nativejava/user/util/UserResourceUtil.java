package com.nativejava.user.util;

import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class UserResourceUtil {

	public MappingJacksonValue getPostBeanMapping(Object T, Set<String> filterSet) {

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterSet);

		FilterProvider provider = new SimpleFilterProvider().addFilter("PostFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(T);
		mapping.setFilters(provider);

		return mapping;
	}

}
