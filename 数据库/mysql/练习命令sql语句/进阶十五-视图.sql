#进阶十五-视图

/**
含义：虚拟表，和普通的表一样使用
mysql5.0.1版本出现的新特性，是通过表动态生成的数据


        创建语法的关键字	是否实际占用物理空间	使用

视图	create view		    只是保存了sql逻辑	    增删改查，只是一般不能增删改

表	    create table		保存了数据		        增删改查

*/

#查询姓张的学生名和专业名
USE students;
INSERT INTO major VALUES(1,'java');
INSERT INTO major VALUES(2,'h5');

INSERT INTO stuinfo VALUES(1,'张三','男',1,18,1),
(2,'张三2','男',2,18,1)

SELECT stuName,majorName FROM stuinfo a JOIN major b
ON a.majorId=b.id
WHERE stuName LIKE '张%';

# 简单使用视图
CREATE VIEW v1
AS
SELECT stuName,majorName FROM stuinfo a JOIN major b
ON a.majorId=b.id


SELECT * FROM v1 WHERE stuName LIKE '张%';


#一、创建视图
/**
语法：
   create view 视图名
   as
   查询语句; 


*/

#1.查询姓名中包含a字符的员工名、部门名和工种信息
-- 1)创建视图
CREATE VIEW myv1
AS
SELECT last_name,department_name,job_title FROM employees e JOIN departments d 
ON e.department_id=d.department_id
JOIN jobs j ON e.job_id=j.job_id;

-- 2)使用视图
SELECT * FROM myv1 WHERE last_name LIKE '%a%';


#2.查询各部门的平均工资级别
-- 1)创建视图查看每个部门的平均工资
CREATE VIEW myv2
AS
SELECT AVG(salary) ag,department_id FROM employees GROUP BY department_id
-- 使用
SELECT * FROM myv2;

SELECT v.ag,g.grade_level FROM myv2 v JOIN job_grades g
ON v.ag BETWEEN g.lowest_sal AND g.highest_sal;


#3.查询平均工资最低的部门信息
-- 1)创建视图查看每个部门的平均工资
CREATE VIEW myv2
AS
SELECT AVG(salary) ag,department_id FROM employees GROUP BY department_id
-- 2)查询最低工资的信息
SELECT * FROM myv2 ORDER BY ag LIMIT 1;

#4.查询平均工资最低的部门名和工资
SELECT department_name,ag FROM (
SELECT * FROM myv2 ORDER BY ag LIMIT 1
) a JOIN departments d ON a.department_id=d.department_id;


#二、修改视图(两种方式)
/**
方式一：
create or replace view 视图名
as 
查询语句;

*/
SELECT * FROM muv1 ;

CREATE OR REPLACE VIEW muv1
AS 
SELECT * FROM employees;


/**
方式二：
alter view 视图名
as 
查询语句;
*/

ALTER VIEW muv1
AS 
SELECT * FROM departments;

SELECT * FROM muv1;

#三、删除视图(可以删除多个)
/**
语法：
    drop view 视图名,视图名,...
*/

DROP  VIEW muv1,myv2;

#四、查看视图
DESC myv2;
SHOW CREATE VIEW myv2;

#五、视图的更新

-- 先创建一个视图
CREATE OR REPLACE VIEW myv1
AS
SELECT last_name,email,salary*12*(1+IFNULL(commission_pct,0)) "annual salary"
FROM employees;

CREATE OR REPLACE VIEW myv1
AS
SELECT last_name,email
FROM employees;

SELECT * FROM myv1;
SELECT * FROM employees;

#1 插入
INSERT INTO myv1 VALUES('wck','wck@163.com');

#2 修改
UPDATE myv1 SET last_name='王五' WHERE last_name='wck';

#3 删除
DELETE FROM myv1 WHERE last_name='王五';

#具备以下特点的视图不允许更新


#1)包含以下关键字的sql语句：分组函数、distinct、group  by、having、union或者union all

CREATE OR REPLACE VIEW myv1
AS
SELECT MAX(salary) m,department_id
FROM employees
GROUP BY department_id;

SELECT * FROM myv1;

#更新
UPDATE myv1 SET m=9000 WHERE department_id=10;

#2)常量视图
CREATE OR REPLACE VIEW myv2
AS

SELECT 'john' NAME;

SELECT * FROM myv2;

#更新
UPDATE myv2 SET NAME='lucy';

#3)Select中包含子查询

CREATE OR REPLACE VIEW myv3
AS

SELECT department_id,(SELECT MAX(salary) FROM employees) 最高工资
FROM departments;

#更新
SELECT * FROM myv3;
UPDATE myv3 SET 最高工资=100000;

#4)join
CREATE OR REPLACE VIEW myv4
AS

SELECT last_name,department_name
FROM employees e
JOIN departments d
ON e.department_id  = d.department_id;

#更新

SELECT * FROM myv4;
-- 这个情况比较特殊，就只有这个更新是可以的
UPDATE myv4 SET last_name  = '张飞' WHERE last_name='Whalen';
-- 添加和删除都不行，删除没试
INSERT INTO myv4 VALUES('陈真','xxxx');

#5)from一个不能更新的视图
CREATE OR REPLACE VIEW myv5
AS

SELECT * FROM myv3;

#更新

SELECT * FROM myv5;

UPDATE myv5 SET 最高工资=10000 WHERE department_id=60;

#6)where子句的子查询引用了from子句中的表

CREATE OR REPLACE VIEW myv6
AS

SELECT last_name,email,salary
FROM employees
WHERE employee_id IN(
	SELECT  manager_id
	FROM employees
	WHERE manager_id IS NOT NULL
);

#更新
SELECT * FROM myv6;
UPDATE myv6 SET salary=10000 WHERE last_name = 'k_ing';

