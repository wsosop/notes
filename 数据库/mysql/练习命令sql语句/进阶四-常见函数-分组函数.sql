#进阶四-常见函数 分组函数
/*
功能：用作统计使用，有称为聚合函数或者统计函数或组函数 由一行来进行显示

1 分类：
    sum 求和、avg 平均值、max 最大值、min 最小值、count 计算个数

2 特点：
    sum 、avg 一般使用与 数值
    max 、min 、count 可使用与任何类型
    
3 以上的分组函数 都忽略 null值  
  以上都可以和 distinct搭配进行去重的运算
 
4 count 的单独介绍  
    效率问题：
    在 MYISAM引擎下 COUNT(*) 效率高
    在 INNODB引擎下 COUNT(*) 和 COUNT(1)效率差不多 但是都高于 COUNT(salary) 这种写法
    
5 和分组函数一同查询的字段要求是 group by 后的字段
*/

#1 简单实用
SELECT SUM(salary) FROM employees;
SELECT AVG(salary) FROM employees;
SELECT MAX(salary) FROM employees;
SELECT MIN(salary) FROM employees;
SELECT COUNT(salary) FROM employees;

#2 一起使用
SELECT SUM(salary) AS 和,AVG(salary) AS 平均值,MAX(salary) AS 最大值,MIN(salary)AS 最小值 ,COUNT(salary) AS 总数
FROM employees;

#3 搭配其他的函数一起使用
SELECT SUM(salary) AS 和,ROUND(AVG(salary),2) AS 平均值,MAX(salary) AS 最大值,MIN(salary)AS 最小值 ,COUNT(salary) AS 总数
FROM employees;

#4 参数自持那些类型
-- SUM、AVG 参数 是 数值型 ，字符型的虽然不会报错，但是毫无意义
SELECT SUM(last_name),AVG(last_name) FROM employees;

-- MAX、MIN 参数是 数值型和字符型，日期型 都可以
SELECT MAX(last_name),MIN(last_name) FROM employees;
SELECT MAX(hiredate),MIN(hiredate) FROM employees;

-- count 参数是 什么类型都可以支持，但是 不统计 值为 null的行 
SELECT COUNT(last_name) FROM employees; -- 107
SELECT COUNT(commission_pct) FROM employees; -- 35

#5 是否忽略 null值
-- SUM、 AVG 、MAX 、MIN 会忽略 null值
SELECT SUM(commission_pct),AVG(commission_pct) FROM employees; 
SELECT MAX(commission_pct),MIN(commission_pct) FROM employees; 

#6 和distinct搭配使用
SELECT SUM(DISTINCT salary),SUM(salary) FROM employees; 
SELECT COUNT(DISTINCT salary),COUNT(salary) FROM employees; 

#7 count的单独详细介绍
/*
效率问题：
在 MYISAM引擎下 COUNT(*) 效率高
在 INNODB引擎下 COUNT(*) 和 COUNT(1)效率差不多 但是都高于 COUNT(salary) 这种写法

*/
-- 非null 计算
SELECT COUNT(salary) FROM employees; 
-- 计算除了所有列都为 null 的行，也即是 所有的行数
SELECT COUNT(*) FROM employees; 
-- 这个1 代表的是 又新增加了 1的一列，有多少行就会有多少个1 
SELECT COUNT(1) FROM employees; 

#8 和分组函数一同查询的字段有限制
-- 这样查询的意义不大
SELECT COUNT(salary) ,employee_id FROM employees; 