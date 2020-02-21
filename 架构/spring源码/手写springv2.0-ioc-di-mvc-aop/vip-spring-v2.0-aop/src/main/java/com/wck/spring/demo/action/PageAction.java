package com.wck.spring.demo.action;

import com.wck.spring.demo.service.IQueryService;
import com.wck.spring.framework.annotation.Autowire;
import com.wck.spring.framework.annotation.Controller;
import com.wck.spring.framework.annotation.RequestMapping;
import com.wck.spring.framework.annotation.RequestParam;
import com.wck.spring.framework.webmvc.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公布接口url
 * @author Tom
 *
 */
@Controller
@RequestMapping("/")
public class PageAction {

	@Autowire
	IQueryService queryService;
	
	@RequestMapping("/first.html")
	public ModelAndView query(@RequestParam("student") String student){
		String result = queryService.query(student);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("student", student);
		model.put("data", result);
		model.put("token", "123456");
		return new ModelAndView("first.html",model);
	}
	
}
