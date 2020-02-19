package com.wck.spring.demo.mvc.service.impl;


import com.wck.spring.annotation.Service;
import com.wck.spring.demo.mvc.service.IDemoService;

@Service
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name;
	}

}
