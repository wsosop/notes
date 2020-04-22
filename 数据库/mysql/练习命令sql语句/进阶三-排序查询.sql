#进阶三-排序查询

/*
语法：
	select 查询列表
	from 表
	【where 筛选条件】
	order by 排序列表 【asc|desc】
	
特点：
	1.asc 代表升序 desc代表降序
	2.order by 子句中 可以支持 单个字段，多个字段，表达式，函数，别名
	3.order by 子句一般放在语句的最末尾，但是 limit子句除外
*/

#1 查询员工的信息按照 工资的从高到低进行排序
SELECT * FROM employees	ORDER BY salary DESC;

#2 查询员工部门编号大于等于 90 的入职时间排序
SELECT * FROM employees WHERE `department_id` >='90' ORDER BY hiredate ASC;

#3 按年薪的高低显示员工的信息和年薪【按表达式排序】
SELECT *,salary*12*(1+IFNULL(commission_pct,0)) AS nian FROM employees ORDER BY nian DESC;

#4 按姓名的长度显示员工的姓名和工资【按函数排序】
SELECT last_name,salary,LENGTH(last_name) AS name_length FROM employees ORDER BY name_length DESC;

#查询员工信息，先按工资排序，再按员工编号排序
SELECT * FROM employees ORDER BY salary DESC,employee_id DESC;