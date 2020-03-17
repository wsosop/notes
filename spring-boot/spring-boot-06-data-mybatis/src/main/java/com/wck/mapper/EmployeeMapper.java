package com.wck.mapper;

import com.wck.bean.Employee;

/**
 * @author 御香烤翅
 * @create 2020-03-17 19:57
 */

//@MapperScan 或者 @Mapper 讲、将接口扫描装配到容器中
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    int insertEmployee(Employee employee);
}
