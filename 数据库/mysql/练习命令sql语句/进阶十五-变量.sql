#进阶十五-变量
/**
系统变量：
        全局变量
        会话变量
        
自定义变量：
        用户变量
        局部变量

*/

#一、系统变量
/**
说明：变量由系统提供，不是用户自定义，属于服务器层面

注意：
如果是全局级别，则需要加global，如果是会话级别，则需要加session,如果不写则默认session

使用语法：
1 查看所有的系统变量
show global|【session】 variables

2 查看满足条件的部分系统变量
show global|【session】 variables like '%查询的关键字%';

3 查看指定的某个系统变量的值
会话变量 
select @@系统变量名;
select @@session.系统变量名;
全局变量
select  @@global.系统变量名;

4 为某个系统变量赋值
方式一：
set global|【session】系统变量名=值;

方式二：
set @@global|【session】.系统变量名=值;


*/
# 一、全局变量
/**
作用域：服务器每次启动将为所有的全局变量赋初始值
        针对所有的会话（连接）有效，但是不能跨重启（即重启后会恢复默认）
        若想要永久有效，则需要配置配置文件
*/

#1 查看所有的系统变量
SHOW GLOBAL VARIABLES;

#2 查看部分的全局变量
SHOW GLOBAL VARIABLES LIKE '%char%';

#3 查看指定的全局变量的值
SELECT @@global.autocommit;
SELECT @@global.tx_isolation;

# 为某个指定的全局变量赋值
SET @@global.autocommit = 0;
SET GLOBAL autocommit = 1;

#二、会话变量
/**
作用域：
    仅仅针对于当前会话（连接）有效


*/

#1 查看所有的会话变量
SHOW VARIABLES; 
SHOW SESSION VARIABLES; 

#2 查看部分会话变量
SHOW SESSION VARIABLES LIKE '%char%';
SHOW VARIABLES LIKE '%char%';

#3 查看指定的某个会话变量
SELECT @@character_set_database;
SELECT @@SESSION.character_set_database;

#4 为某个会话变量赋值
SET @@session.tx_isolation='read-uncommitted';
SET SESSION tx_isolation='read-committed';


#二、自定义变量
/**
变量是用户自定义的，不是由系统提供的

使用步骤：
声明
赋值
使用（查看、比较、运算等）
*/

#1 用户变量
/**
作用域：针对当前的会话（连接）有效，同于会话变量的作用域
应用：应用于任何地方，也就是begin end 里面 或者外面begin end都行
*/

#1)声明并初始化【赋值的操作符为= 或者 :=】
SET @用户变量名=值;或
SET @用户变量名:=值;或
SELECT @用户变量名:=值;

#2)赋值（更新用户变量的值）
-- 方式一：通过set 或 select 
        SET @用户变量名=值;或
        SET @用户变量名:=值;或
        SELECT @用户变量名:=值;

-- 方式二：通过select into 
          SELECT 字段 INTO @变量名 FROM 表;

#3) 使用（查看用户变量的值）
SELECT @用户变量名;


#如：
SET @name='john';
SET @name=100;

SET @count=1;

SELECT COUNT(*) INTO @count FROM employees;

SELECT @count;
SELECT @name;

#2 局部变量
/**
作用域：仅仅在定义它的begin end 中有效
应用于：begin end 中第一句话！！！
*/

#1)声明
DECLARE 变量名 类型;
DECLARE 变量名 类型 【DEFAULT 值】;


#2)赋值（更新变量的值）

#方式一：
	SET 局部变量名=值;
	SET 局部变量名:=值;
	SELECT 局部变量名:=值;
#方式二：
	SELECT 字段 INTO 具备变量名
	FROM 表;
#3)使用（查看变量的值）
SELECT 局部变量名;

#如：声明两个变量，求和并打印

#用户变量
SET @m=1;
SET @n=1;
SET @sum=@m+@n;
SELECT @sum;

#局部变量 应用于：begin end 中第一句话！！！ 这句话会报错
DECLARE m INT DEFAULT 1;
DECLARE n INT DEFAULT 1;
DECLARE SUM INT;
SET SUM=m+n;
SELECT SUM;

#用户变量和局部变量的对比
    
            作用域			        定义位置		        语法
用户变量	当前会话		        会话的任何地方		    加@符号，不用指定类型
局部变量	定义它的BEGIN END中 	BEGIN END的第一句话	    一般不用加@,需要指定类型

3 SHOW VARIABLES LIKE "%count%";


