package com.wck.mybatis.dao;

import com.wck.mybatis.bean.Department;

public interface DepartmentMapper {
	
	//根据id获取部门
	Department getDepartmentById(Integer id);
	
	//使用collection定义关联集合类型的属性的封装规则，即部门中查出所有的员工使用List接收
	public Department getDeptByIdPlus(Integer id);
	
	//通过分段查询
	public Department getDeptByIdStep(Integer id);


}
