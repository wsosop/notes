package com.wck.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.wck.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	//使用map返回多条记录，key 是 主键的值，value为 Employee对象
	@MapKey("id")
	//告诉mybatis封装这个map的时候，使用哪个主键作为key
	Map<Integer, Employee> getEmployeeByLastNameLikeMap(@Param("lastName") String lastName);
	
	//使用Map来作为一个记录返回值
	Map<Integer,Object> getEmployeeByIdReturnMap(@Param("id") Integer id);
	
	//使用List返回的值
	List<Employee> getEmployeeByLastNameLike(@Param("lastName") String lastName);
	
	//使用$来取表名或者排序测试
	Employee getEmployeeByIdAndLastNameOther(@Param("tableName") String tableName,@Param("id") Integer id,@Param("lastName") String lastName);

	
	Employee getEmployeeByMap(Map<String, Object> map);
	
	Employee getEmployeeByIdAndLastName(@Param("id") Integer id,@Param("lastName") String lastName);
	
	//查找方法
	Employee getEmployeeById(Integer id);
	
	//增加方法
	void addEmployee(Employee employee);
	
	//更新方法
	void updateEmployee(Employee employee);

	//删除方法
	void deleteEmployee(Employee employee);
	


	
	
	

}
