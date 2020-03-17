package com.wck.controller;

import com.wck.bean.Department;
import com.wck.bean.Employee;
import com.wck.mapper.DepartmentMapper;
import com.wck.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 御香烤翅
 * @create 2020-03-17 19:35
 */

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/getDepartment")
    public Department getDepartment(){
        return departmentMapper.findByid(1);
    }


    @GetMapping("/department")
    public Department insertDept(Department department){
        departmentMapper.insertDepartment(department);
        return department;
    }

    @GetMapping("/insertEmployee")
    public Employee insertEmployee(Employee employee){
        employeeMapper.insertEmployee(employee);
        return  employee;
    }

    @GetMapping("/findEmployeeById/{id}")
    public Employee findEmployeeById(@PathVariable("id") Integer id){
        Employee employeeById = employeeMapper.getEmployeeById(id);
        return  employeeById;
    }
}
