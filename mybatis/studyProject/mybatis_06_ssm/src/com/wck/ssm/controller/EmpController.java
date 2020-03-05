package com.wck.ssm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wck.ssm.bean.Employee;
import com.wck.ssm.service.EmployeeService;

@Controller
public class EmpController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("emps")
	public String getEmps(Map<String,Object> map) {
		System.out.println("进入了这个emps");
		List<Employee> eList=employeeService.getEmps();
//		System.out.println(eList);
		map.put("allEmps", eList);
		return "list";
	}
	
	@RequestMapping("addEmp")
	@ResponseBody
	public void addEmp() {
		System.out.println("进入了这个addEmp");
		employeeService.addEmp();
	}
	
	@RequestMapping("addBatchEmp")
	@ResponseBody
	public void addBatchEmp() {
		System.out.println("进入了这个addBatchEmp");
		employeeService.addEmpBatch();
	}
	
	

}
