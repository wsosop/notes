package com.wck.mybatis.dao;

import java.util.List;

import com.wck.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

	//指定id查询Employee对象
	Employee getEmployee(Integer id);
	
	//使用级联连表查询
	Employee getCaseEmployee(Integer id);
	
	//通过association级联，分步查询
	Employee getCaseEmployeeByStep(Integer id);
	
	//通过部门的id，来查询所有员工
	List<Employee> getEmployeesList(Integer deptId);

}
