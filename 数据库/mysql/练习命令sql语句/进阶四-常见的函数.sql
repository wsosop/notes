#进阶四-常见的函数

/*

调用：select 函数名（实参列表） 【from 表】;
分类：
	1.单行函数 如 contact 、length、ifnull等
	2.分组函数 ：做统计使用，又称为 统计函数、聚合函数、组函数
	

*/

/*

常见函数：
	一、单行函数
	字符函数：
	length:获取字节个数(utf-8一个汉字代表3个字节,gbk为2个字节)
	concat
	substr
	instr
	trim
	upper
	lower
	lpad
	rpad
	replace
	
	数学函数：
	round
	ceil
	floor
	truncate
	mod
	
	日期函数：
	now
	curdate
	curtime
	year
	month
	monthname
	day
	hour
	minute
	second
	str_to_date
	date_format
	其他函数：
	version
	database
	user
	控制函数
	if
	case
*/

#一、字符函数
#1 length 获取字节长度
SELECT LENGTH('wck'); -- 3
SELECT LENGTH('你好wck'); -- 9 ,由于是utf8的字符集，所以 每个汉字占 三个 字节

#2 contanct 拼接字符串
SELECT CONCAT("张三","-",'李四');

#3 upper lower
SELECT UPPER("中文wck");-- 中文WCK
SELECT LOWER("中文WCK");-- 中文wck

#4 substr sunstring
-- 索引从 1 开始
SELECT SUBSTR("你好世界哈哈哈",2) AS OUT_PUT;
-- 这个 3 代表从第三位开始截取，2表示 截取两个长度
SELECT SUBSTR("你好世界哈哈哈",1,1) AS OUT_PUT;

-- 姓名中首字母大写，其他的字母小写，并且用下划线连接起来
SELECT CONCAT (UPPER(SUBSTR(last_name,1,1)) ,"_",LOWER(SUBSTR(last_name,2))) FROM employees;

#5 instr 第二个参数在 第一个参数中第一次 出现的索引位置，找不到则返回 0
SELECT INSTR("你好世界哈哈哈好世界哈",'世界') AS out_put; -- 3

#6 trim 去除左右两边的空格 或者 指定 字符
SELECT LENGTH (TRIM("    abcd   ")) AS out_put; -- 4

-- 去除前后的 所有a 
SELECT TRIM('a' FROM "aaaaaaaa你好aaaaahahah哈哈aaaaaaaaa") out_put; -- 你好aaaaahahah哈哈

#7 lpad 用指定的字符实现 左填充指定长度
SELECT LPAD("hello",10,'*') AS  out_put; -- *****hello
-- 如果指定的长度 小于原字符的长度，则截取指定的长度，从左边开始
SELECT LPAD("hello",2,'*') AS  out_put; -- he

#8 rpad 用指定的字符实现 右填充指定长度
SELECT RPAD("hello",10,'*') AS  out_put; -- hello*****
-- 如果指定的长度 小于原字符的长度，则截取指定的长度，从左边开始

#9 replace 把指定的字符串替换成指定的字符串 ，只要是能匹配的，所有的全部替换
SELECT REPLACE("hello你好世界hello","hello","你好世界") AS out_put; -- 你好世界你好世界你好世界


#二、数学函数
#10 round 四舍五入 当是负数的时候，先对值取四舍五入，在加负号
SELECT ROUND(1.65) AS out_put; -- 2
SELECT ROUND(-1.65) AS out_put; -- -2
-- 第二个参数 指定保留的位数
SELECT ROUND(1.657,2) AS out_put; -- 1.66

#11 ceil 向上取整,返回 >=该参数的最小整数
SELECT CEIL(1.65) AS out_put; -- 2
SELECT CEIL(-1.65) AS out_put; -- -1

#12 floor 向下取整 <=该参数的最大整数
SELECT FLOOR(1.99) out_put; -- 1
SELECT FLOOR(-1.99) out_put; -- 2

#13 TRUNCATE 截断
SELECT TRUNCATE(1.67999,1) AS out_put; -- 1.6

#14 mod 取余
/*
	mod(a,b) : a-a/b*b
	mod(-10,-3) ： (-10) - (-10)/(-3) * (-3) = -1

*/
SELECT MOD(10,3) AS out_put; -- 1
SELECT 10%3 AS out_put; -- 1
SELECT MOD(-10,-3) AS out_put; -- -1


#三、日期函数

#15 now() 返回当前系统的日期 + 时间
SELECT NOW() ; -- 2020-04-11 16:50:47

#16 curdate() 返回当前的日期，不包含时间
SELECT CURDATE(); -- 2020-04-11

#17 curtime() 返回当前的时间，不包含日期
SELECT CURTIME(); -- 16:53:01

#18 可以获取指定的某一部分，如 年、月、日、时、分、秒
SELECT YEAR(NOW()) 年; -- 2020
SELECT YEAR('1999-9-7') 年; -- 1999
SELECT YEAR(`hiredate`) 年 FROM employees; 
SELECT MONTH(NOW()) 月; -- 4
SELECT MONTHNAME(NOW()) 月; -- April

#19 str_to_date 字符串转换成日期的
SELECT STR_TO_DATE("09-03-2020","%d-%m-%Y") AS `date`; -- 2020-03-09

#20 date_formate
SELECT DATE_FORMAT(NOW(),"%y年-%m月-%d日") AS `date`; -- 20年-04月-11日

#四、其他函数

#21 version() 查看数据库版本
SELECT VERSION();-- 5.5.15

#22 database() 查看数据库名称
SELECT DATABASE(); -- myemployees

#23 user() 查看当前用户
SELECT USER(); -- root@localhost


#五、流程控制函数
#24 if  if else 的效果 ，类似于 java的三元运算符
SELECT IF(10>5 ,"大于","小于") AS out_put;
SELECT last_name ,commission_pct ,IF(commission_pct IS NULL ,"没奖金","有奖金") FROM employees;

#25 case 函数使用一： switch case 的效果

/**
java 中
	switch (变量或者表达式)
	   case 常量1：语句1 ;break;
	   ...
	   case 常量n：语句n ;break;
	   default:语句;break;

mysql中
	case 要判断的字段或表达式
		when 常量1 then 要显示的值1或语句1;
		...
		when 常量n then 要显示的值n或语句n;
		else 要显示的值或者或者语句;
	end

*/

/*
查询员工的工资
部门号为 30 的，显示工资为1.1倍
部门号为 40 的，显示工资为1.2倍
部门号为 50 的，显示工资为1.3倍
其他部门，显示的为原工资
*/

SELECT salary ,department_id,
CASE department_id
  WHEN 30 THEN salary*(1+0.1)
  WHEN 40 THEN salary*(1+0.2)
  WHEN 50 THEN salary*(1+0.3)
  ELSE salary
END AS newSalary 
FROM employees;


#26 case 函数使用二： 类似于多重if 
/*

java 中

if(判断条件1){
执行的语句1
}else if(判断条件2){
执行的语句2
}else if(判断条件3){
执行的语句3
}else{
执行的语句
}

mysql 中

case
    when 条件1 then 要显示的值1或 语句1;
    ...
    when 条件n then 要显示的值n或 语句n;
    else 要显示的值或 语句;
end    
    
*/

/**
查询员工的工资情况
工资>20000 显示A级别
工资>15000 显示B级别
工资>10000 显示C级别
否则显示 D级别
*/

SELECT salary,
CASE 
    WHEN salary > 20000 THEN 'A'
    WHEN salary > 15000 THEN 'B'
    WHEN salary > 10000 THEN 'C'
    ELSE 'D'
END AS LEVEL    
FROM employees;    










 



