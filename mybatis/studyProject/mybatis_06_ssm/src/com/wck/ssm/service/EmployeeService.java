package com.wck.ssm.service;

import java.util.List;

import com.wck.ssm.bean.Employee;

public interface EmployeeService {

	public List<Employee> getEmps();
	
	public boolean addEmp();
	
	//执行批量插入
	public void addEmpBatch();
	
	
	
}
