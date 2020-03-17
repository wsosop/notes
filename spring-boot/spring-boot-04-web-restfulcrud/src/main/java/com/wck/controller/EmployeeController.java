package com.wck.controller;

import com.wck.dao.DepartmentDao;
import com.wck.dao.EmployeeDao;
import com.wck.entities.Department;
import com.wck.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author 御香烤翅
 * @create 2020-03-14 21:43
 */

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    Logger logger = LoggerFactory.getLogger(getClass());


    //员工列表
    @GetMapping("/emps")
    public String list(Model model){
        logger.info("请求所有员工");
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //显示添加员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model){

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //springMVC自动将请求参数和入参对象进行一一绑定
    @PostMapping("/emp")
    public String addEmp(Employee employee){
       //redirect 重定向到一个页面  /代表当前项目路径
        //forward 转发到一个页面
        logger.info("要保存的员工信息：{}",employee.toString());
        employeeDao.save(employee);
        //具体查阅这个 org.thymeleaf.spring4.view.ThymeleafViewResolver.createView
        return "redirect:/emps";
    }


    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        logger.info("获取到的id为：{}",id);

        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        //redirect 重定向到一个页面  /代表当前项目路径
        //forward 转发到一个页面
        logger.info("要修改的员工信息：{}",employee.toString());
        //具体查阅这个 org.thymeleaf.spring4.view.ThymeleafViewResolver.createView
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("emp/{id}")
    public String deleteEmps(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }





}
