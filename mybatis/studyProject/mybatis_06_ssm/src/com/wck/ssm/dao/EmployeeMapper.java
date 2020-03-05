package com.wck.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wck.ssm.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id); 
	
	public List<Employee> getEmps();
	
	public boolean addEmp(@Param("emp") Employee employee) ;
	
	

}
