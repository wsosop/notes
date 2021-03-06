#进阶五-分组查询

/*
语法：
    select 分组函数，列（要求在分组列表中出现的列）
    from 表名
    【where 筛选条件】
    group by 分组列表
    【order by 子句】 

注意： 
    查询列表比较特殊，必须是分组函数和group by 后出现的字段

特点：
    1. 分组查询中的条件分为两类
    
                数据源             位置                      关键字
    分组前筛选  原始表             group by 子句的前面       where
    分组后筛选  分组后的结果集     group by 子句的后面       having
    
    分组函数做条件，一定是放在having中
    
    2.group  by 子句支持单个字段分组、多个字段分组（多个字段之间用逗号隔开，没有先后顺序之分）、表达式或函数（用的较少）
    3.也可以使用排序，但是排序是放在整个查询之后的

*/

#1 简单查询开始:查询每个工种的最高工资
SELECT MAX(salary) ,job_id FROM employees
GROUP BY job_id;

#2 查询每个位置上的部门个数
SELECT COUNT(department_id) ,location_id FROM departments
GROUP BY location_id;

#3 查询邮箱中包含a字母的，每个部门的平均工资
SELECT AVG(salary),department_id FROM employees
WHERE email LIKE '%a%'
GROUP BY department_id;

#4 查询有奖金的每个领导手下的员工的最高工资
SELECT MAX(salary) ,manager_id FROM employees
WHERE commission_pct IS NOT NULL 
GROUP BY manager_id;

#5 复杂查询开始：查询那个部门的员工个数大于2
-- 添加分组后的筛选

-- i 先查询所有的员工个数和部门号
SELECT COUNT(employee_id) ,department_id FROM employees
GROUP BY department_id;

-- ii 再查询 统计的个数大于 2 的 使用 having 

SELECT COUNT(employee_id) AS emp_count ,department_id FROM employees
GROUP BY department_id
HAVING emp_count >2;

#6 查询每个工种有奖金的员工的最高工资>12000的工种编号和最高工资

SELECT job_id,MAX(salary) FROM employees 
WHERE commission_pct IS NOT NULL
GROUP BY job_id
HAVING MAX(salary) >12000;

#7 查询领导编号>102的每个领导手下最低工资>5000的领导的编号是哪个，以及其最低工资
SELECT MIN(salary) AS min_salary,manager_id FROM employees
WHERE manager_id > 102
GROUP BY manager_id
HAVING min_salary >5000;

#8 按员工姓名的长度分组，查询每一组的员工个数，筛选员工个数 > 5 的有哪些
SELECT COUNT(*) AS result,LENGTH(last_name)
FROM employees
GROUP BY LENGTH(last_name)
HAVING result >5;

# 按多个字段分组
#9 查询每个部门每个工种的员工的平均工资
SELECT AVG(salary),department_id,job_id FROM employees
GROUP BY department_id,job_id;
 
# 添加排序
#10 查询每个部门每个工种的员工的平均工资，并且按照平均工资的高低显示出来
SELECT AVG(salary),department_id,job_id FROM employees
GROUP BY department_id,job_id
ORDER BY AVG(salary) DESC;


