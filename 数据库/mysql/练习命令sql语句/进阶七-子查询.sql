#进阶7-子查询
/**
含义：
    出现在其他语句中的select语句，称为子查询或内查询
    外部查询语句，称为主查询或外查询

分类：    
    按子查询出现的位置：
        1 select后面:
            仅仅支持 标量子查询（结果集只有一行一列）
        2 from 后面:
            表子查询（结果集有多行多列）
        3 where或having后面: （重点）
            标量子查询（结果集只有一行一列）（*）
            列子查询（结果集只有一列多行）（*）
            
            行子查询（结果集有一行多列/多行多列）
        4 exists后面（相关子查询）
            表子查询（结果集有多行多列）
            
     按结果集的行列数不同：
        1 标量子查询（结果集只有一行一列）
        2 列子查询（结果集只有一列多行）
        3 行子查询（结果集有一行多列）
        4 表子查询（结果集有多行多列）
                
*/

# 一、where或having后面: （重点）
/**
1 标量子查询（结果集只有一行一列）又称为单行子查询
2 列子查询（结果集只有一列多行） 又称为多行子查询
3 行子查询（结果集有一/多行多列）
特点：
①子查询放在小括号内
②子查询一般放在条件的右侧
③标量子查询，一般搭配着单行操作符使用
> < >= <= = <>

列子查询，一般搭配着多行操作符使用
in、any/some、all

操作符             含义
IN/NOT IN          等于列表中的任意一个
ANY|SOME           和子查询返回的某一个值比较
ALL                和子查询返回的所有值比较       

④子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果
*/

#标量子查询
#1 谁的工资比Abel的工资高
SELECT * FROM employees 
WHERE salary >
(SELECT salary FROM employees WHERE last_name ='Abel')

#2 返回job_id 与141号员工相同，salary比143号员工多的员工姓名，job_id 和工资
SELECT last_name,job_id,salary FROM employees
WHERE job_id=(
SELECT job_id FROM employees WHERE employee_id = 141
) AND salary > (
SELECT salary FROM employees WHERE employee_id = 143
)

#3 返回公司工资最少的员工的last_name,job_id和salary
SELECT last_name,job_id,salary FROM employees WHERE 
salary =(
SELECT MIN(salary) FROM employees
);

#4 查询最低工资大于50号部门最低工资的部门id和其最低工资

SELECT department_id,MIN(salary) FROM employees GROUP BY department_id
HAVING MIN(salary) > (
    SELECT MIN(salary) FROM employees WHERE department_id=50
)

#列子查询（结果集只有一列多行）又称为多行子查询
#1 返回location_id是1400或1700的部门中的所有员工姓名
SELECT last_name FROM employees WHERE department_id IN
(
    SELECT DISTINCT department_id FROM departments WHERE location_id IN(1400,1700)
)

#返回其它工种中比job_id为‘IT_PROG’工种任一工资低的员工的员工号、姓名、job_id 以及salary
SELECT employee_id,last_name,job_id,salary FROM employees 
WHERE salary < ANY (
SELECT DISTINCT salary FROM employees WHERE job_id ='IT_PROG'
) AND job_id !='IT_PROG'

-- 或者是这种写法
SELECT employee_id,last_name,job_id,salary FROM employees 
WHERE salary <  (
SELECT DISTINCT MAX(salary) FROM employees WHERE job_id ='IT_PROG'
) AND job_id !='IT_PROG'

#返回其它工种中比job_id为‘IT_PROG’工种所有工资都低的员工的员工号、姓名、job_id 以及salary

SELECT employee_id,last_name,job_id,salary FROM employees 
WHERE salary < ALL (
SELECT DISTINCT salary FROM employees WHERE job_id ='IT_PROG'
) AND job_id !='IT_PROG'

-- 或者是这种写法

SELECT employee_id,last_name,job_id,salary FROM employees 
WHERE salary < (
SELECT  MIN(salary) FROM employees WHERE job_id ='IT_PROG'
) AND job_id !='IT_PROG'

# 行子查询（结果集有一行多列/多行多列）
# 查询员工编号最小并且工资最高的员工信息
-- 行子查询的方式
SELECT * FROM employees WHERE (employee_id,salary)=(
    SELECT MIN(employee_id),MAX(salary) FROM employees
)

-- 原先的写法方式
SELECT * FROM  employees WHERE 
employee_id =(
    SELECT MIN(employee_id) FROM employees
) AND 
salary = (
    SELECT MAX(salary) FROM employees
)

# 二、select 后面
#查询每个部门的信息外加员工个数
SELECT d.* ,(
    SELECT COUNT(*) FROM employees e 
    WHERE e.department_id=d.department_id
) AS 个数
FROM departments d;

#查询员工号等于102的部门名
SELECT (SELECT department_name FROM departments d 
    WHERE e.department_id=d.department_id)
AS department_name
FROM employees e WHERE employee_id=102

# 三、from 后面
# 查询每个部门的平均工资的工资等级
/**
将子查询的结果充当表，要求必须起别名
*/
SELECT j.grade_level,result,department_id  FROM job_grades j,(
SELECT AVG(salary) AS result,department_id FROM employees
GROUP BY department_id
) e WHERE e.result BETWEEN lowest_sal AND highest_sal;


#四、exists后面（相关子查询）
/**
判断EXISTS 里面的查询是否有值 有的话 返回 1 没有 返回 0
*/
SELECT EXISTS(SELECT last_name FROM employees); -- 1
SELECT EXISTS(SELECT last_name FROM employees WHERE salary =30000); -- 0 

#查询有员工的部门名

SELECT department_name FROM departments d WHERE EXISTS(
SELECT * FROM employees e WHERE d.department_id=e.department_id
)

















