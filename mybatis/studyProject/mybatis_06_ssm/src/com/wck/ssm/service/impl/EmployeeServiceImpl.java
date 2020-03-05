package com.wck.ssm.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wck.ssm.bean.Department;
import com.wck.ssm.bean.Employee;
import com.wck.ssm.dao.EmployeeMapper;
import com.wck.ssm.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private SqlSession sqlSessionBatch;
	
	@Override
	public List<Employee> getEmps() {
		
		PageHelper.startPage(1, 3);
		List<Employee> emps = employeeMapper.getEmps();
		
		return emps;
	}

	@Override
	public boolean addEmp() {
		//耗时：4051
		System.out.println("非批量插入...");
		long start = System.currentTimeMillis();
		
		for(int i=0;i<1000;i++) {
			employeeMapper.addEmp(new Employee(null, UUID.randomUUID().toString().substring(1, 5), "wangwu", "1", new Department(1)));
		}
		
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		
		return true;
	}

	@Override
	public void addEmpBatch() {
		//耗时：2049
		System.out.println("批量插入...");
		long start = System.currentTimeMillis();
		EmployeeMapper mapper = sqlSessionBatch.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			mapper.addEmp(new Employee(null, UUID.randomUUID().toString().substring(1, 5), "wangwu", "1", new Department(1)));
		}
		System.out.println("耗时："+(System.currentTimeMillis()-start));
	}
	
	
	

}
