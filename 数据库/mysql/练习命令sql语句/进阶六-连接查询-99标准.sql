#99连接查询
/**
语法：
    select 字段列表 
    from 表1 别名 【连接类型】
    join 表2 别名
    on 连接条件
    【where 筛选条件】
    【group by 分组条件】
    【order by 排序列表】
 
分类：
    
内连接（*） inner 

外连接
    左外连接（*）: left 【outer】
    右外连接（*）: right 【outer】
    全外连接: full【outer】
    
交叉连接：cross
*/

# 一、内连接
/**
语法：
    select 查询列表 
    from 表1 
    inner join 表2 on 连接条件
    【where筛选条件】
    【group by 条件】
    【oeder by 排序条件】
    
特点：
    1 可以添加排序、分组、筛选
    2 inner可以省略
    3 筛选条件放在where后面，连接条件放在on后面，提高分离性，便于阅读
    4 inner join 和92 语法的等值连接效果是一样的，都是查询多表的交集部分
*/

#1）、等值连接
#案例1.查询员工名、部门名

SELECT last_name,department_name
FROM departments d
 JOIN  employees e
ON e.`department_id` = d.`department_id`;



#案例2.查询名字中包含e的员工名和工种名（添加筛选）
SELECT last_name,job_title
FROM employees e
INNER JOIN jobs j
ON e.`job_id`=  j.`job_id`
WHERE e.`last_name` LIKE '%e%';



#3. 查询部门个数>3的城市名和部门个数，（添加分组+筛选）

#①查询每个城市的部门个数
#②在①结果上筛选满足条件的
SELECT city,COUNT(*) 部门个数
FROM departments d
INNER JOIN locations l
ON d.`location_id`=l.`location_id`
GROUP BY city
HAVING COUNT(*)>3;


#4 查询那个部门的部门员工个数>3的部门名和员工个数，并按个数排序
SELECT department_name,COUNT(*) FROM employees e INNER JOIN departments d
ON  e.department_id=d.department_id
GROUP BY d.department_id
HAVING COUNT(*) >3 
ORDER BY COUNT(*) DESC;

#5 查询员工名、部门名、工种名，并按部门名降序
SELECT e.last_name,d.department_name,j.job_title FROM employees e
INNER JOIN departments d
ON e.department_id=d.department_id INNER JOIN jobs j ON
e.job_id=j.job_id
ORDER BY d.department_name DESC;

#2）、非等值连接
#6 查询员工的工资级别
SELECT salary,grade_level FROM employees e JOIN job_grades j
ON e.salary BETWEEN j.lowest_sal AND j.highest_sal;

#7 查询员工的工资级别的个数，并且降序
SELECT COUNT(*) ,grade_level FROM employees e JOIN job_grades j
ON e.salary BETWEEN j.lowest_sal AND j.highest_sal
GROUP BY grade_level
ORDER BY COUNT(*) DESC;

#3） 自连接
#8 查询员工的姓名和对应领导的姓名
SELECT a.department_id,a.last_name,b.department_id,b.last_name FROM employees a JOIN employees  b
ON a.manager_id =b.employee_id;

#二、外连接
/**
应用场景：
    用于查询一个表中有，另一个表中没有的记录场景
*/
#左外连接 
#1 查询女神表中所有的男神数据，没有男神的用null 代替 
SELECT * FROM beauty b LEFT JOIN boys bo
ON b.boyfriend_id=bo.id;
 
#右外连接 
#2 查询女神表中所有的男神数据，没有男神的用null 代替 

SELECT * FROM boys bo RIGHT JOIN beauty b 
ON b.boyfriend_id=bo.id;


#案例
# 左外： 查询那个部门没有员工
SELECT * FROM departments d LEFT JOIN employees e 
ON d.department_id=e.department_id
WHERE e.employee_id IS NULL;

#右外：查询那个部门没有员工
SELECT * FROM employees e  RIGHT JOIN departments d 
ON d.department_id=e.department_id
WHERE e.employee_id IS NULL;

#全外连接
# 全外连接相当于  =内连接的结果+表1中有但表2没有的+表2中有但表1没有的
 
#交叉连接 相当于是笛卡尔乘积  boys 这个有四条数据  beauty 这个有 12条数据 查询出来的总数据为 48条
SELECT b.*,bo.* FROM beauty b CROSS JOIN boys bo; 
 

SELECT * FROM boys bo LEFT JOIN beauty b ON bo.id=b.boyfriend_id
WHERE b.boyfriend_id IS NULL;







