#进阶六-连接查询-92标准
/**
又称多表查询；当查询的字段来自多个表时，就会用到多表查询

分类：
    按年代分类：
        sql92标准： 仅仅支持内连接
        sql99标准【推荐】：支持内连接+外连接（左外连接和右外连接）+交叉连接
        
    按功能分类：
        内连接：
                等值连接
                非等值连接
                自连接
        外连接：
                左外连接
                右外连接
                全外连接
        交叉连接
        
            
        

*/
SELECT * FROM beauty;
SELECT * FROM boys;

#一  sql92标准： 仅仅支持内连接
/**
特点：
    1.多表等值连接的结果为多表的交集部分
    2.n表连接至少需要 n-1 个连接条件
    3.多表连接顺序没有要求
    4.一般多表连接都会起别名
    5.可以搭配前面学习到的所有子句使用，比如：排序、分组、筛选

*/

#一、 等值连接
-- 1.查询女神名对应的男神名
SELECT `name` ,boyName FROM beauty,boys WHERE beauty.boyfriend_id=boys.id;

#2 查询员工名和对应的部门名
SELECT last_name,department_name FROM employees a,departments b
WHERE a.department_id=b.department_id;

#3 查询员工名，工种号，工种名
SELECT last_name,a.job_id,job_title FROM employees a,jobs b
WHERE a.job_id=b.job_id;

#4 查询有奖金的员工名和部门名
SELECT last_name,department_name FROM employees a,departments b
WHERE a.department_id=b.department_id AND commission_pct IS NOT NULL;

#5 查询城市名中第二个字符为o 的部门名和城市名
SELECT department_name,city FROM employees a,departments b ,locations c
WHERE a.department_id=b.department_id AND b.location_id=c.location_id
AND city LIKE '_o%';

#6 查询每个城市的部门个数
SELECT COUNT(*),city FROM departments a,locations b
WHERE a.location_id=b.location_id
GROUP BY b.city;

# 加分组
#7 查询有奖金的每个部门的部门名和部门的领导编号和该部门的最低工资
SELECT department_name,a.manager_id,MIN(salary) FROM employees a ,departments b
WHERE a.department_id=b.department_id
AND a.commission_pct IS NOT NULL 
GROUP BY b.department_name,b.manager_id;

# 加排序
#8 查询每个工种的工种名和员工的个数，并且按员工个数降序
SELECT job_title,COUNT(*) AS result FROM employees a,jobs b
WHERE a.job_id=b.job_id
GROUP BY b.job_id
ORDER BY result DESC;

#二、 非等值连接
#9 查询员工的工资和工资级别
-- 也可以增加 其他的条件
SELECT salary,grade_level FROM employees a,job_grades b
WHERE a.salary BETWEEN b.lowest_sal AND b.highest_sal;

#三、 自连接
#10 查询员工名和上级的名字
SELECT e.employee_id,e.last_name ,m.employee_id 领导ID,m.last_name 领导名
FROM employees e, employees m
WHERE e.manager_id=m.department_id;





