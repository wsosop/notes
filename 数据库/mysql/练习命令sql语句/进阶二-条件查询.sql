#进阶二-条件查询
/*
语法：
    select 
      查询列表
    from 
      表名
    where
      筛选条件;

分类：
   一、按条件表达式筛选
       简单条件运算符： > < = != <> >= <=
   二、按逻辑表达式筛选
       逻辑运算符：&& || ！ and or not
        -- and（&&）:两个条件如果同时成立，结果为true，否则为false
	-- or(||)：两个条件只要有一个成立，结果为true，否则为false
	-- not(!)：如果条件成立，则not后为false，否则为true   
   三、模糊查询
       like 
       between and
       in 
       is null                 
*/
# 一、按条件表达式筛选
#1(>) 查询工资大于 12000的员工信息
SELECT * FROM employees WHERE salary > 12000;

#2(<>) 查询部门编号不等于 90 的员工的姓名和部门编号
SELECT last_name,department_id FROM employees WHERE department_id <> 90;

# 二、按逻辑表达式筛选
	-- and（&&）:两个条件如果同时成立，结果为true，否则为false
	-- or(||)：两个条件只要有一个成立，结果为true，否则为false
	-- not(!)：如果条件成立，则not后为false，否则为true

#1(and) 查询工资在10000到20000之间的员工
SELECT * FROM employees WHERE salary >=10000 AND salary <=20000;

#2(or) 查询部门编号不是在90和110之间，或者工资高于15000的员工信息`tbl_dept`
SELECT * FROM employees WHERE department_id <90 OR department_id > 110 OR salary >15000;
#(not)
SELECT * FROM employees WHERE NOT( department_id >=90 AND department_id<=110) OR salary > 15000;

#三、模糊查询
/*     like  常用通配符 
		%：任意多个字符，包含0个字符
		_: 任意单个字符
       between and
       in 
       is null  
*/
#1(LIKE) 查询员工姓名中包含a的员工信息
SELECT * FROM employees WHERE last_name LIKE '%a%';

#2(LIKE) 查询员工姓名中包含第三个字符为n,第五个字符为l的员工信息
SELECT * FROM employees WHERE last_name LIKE '__n_l%'; 

#3(LIKE) 查询员工姓名中包含第二个字符为_的员工信息
SELECT * FROM employees WHERE last_name LIKE '_\_%'; 
SELECT * FROM employees WHERE last_name LIKE '_$_%' ESCAPE '$'; 

#4(BETWEEN AND) 查询员工编号在100和120之间的员工信息
SELECT * FROM employees WHERE employee_id BETWEEN 100 AND 120;

#5(in) 查询员工工种编号是PU_MAN、FI_ACCOUNT、ST_MAN的员工信息
SELECT * FROM employees WHERE job_id IN('PU_MAN','FI_ACCOUNT','ST_MAN');

#6(IS NULL、IS NOT NULL) 查询没有奖金的员工信息
/**
=或者<> 不能判断null值
is null或 is not null 可以判断 null
*/
SELECT * FROM employees WHERE commission_pct IS NULL;
SELECT * FROM employees WHERE commission_pct IS NOT NULL;

# 安全等于  <=>
#7 (<=>) 查询没有奖金的员工信息
SELECT * FROM employees WHERE commission_pct <=> NULL;

#8 (<=>) 查询员工为 12000工资的员工信息

/*

is null  和安全等于的对比

is null PK <=>
			普通类型的数值	null值		可读性
is null		×			√		√
<=>		√			√		×

*/

#查询员工号为176的员工姓名和部门号和年薪(注意这里面有年利率为空的)
SELECT last_name,department_id,salary*12*(1+IFNULL(commission_pct,0)) AS '年薪'
FROM employees;



	