package com.wck.spring.demo.mvc.action;


import com.wck.spring.annotation.Autowried;
import com.wck.spring.annotation.Controller;
import com.wck.spring.annotation.RequestMapping;
import com.wck.spring.demo.mvc.service.IDemoService;

@Controller
public class MyAction {

		@Autowried
		IDemoService demoService;
	
		@RequestMapping("/index.html")
		public void query(){

		}
	
}
