#进阶十六-存储过程
/**
存储过程和函数：类似于java中的方法
好处：
1、提高代码的重用性
2、简化操作
*/

#存储过程
/**
含义：一组预先编译好的SQL语句的集合，理解成批处理语句
1、提高代码的重用性
2、简化操作
3、减少了编译次数并且减少了和数据库服务器的连接次数，提高了效率

*/

#一、创建
/**
语法：
create procedure 存储过程名(参数列表)
BEGIN
    存储过程体（一组合法的SQl语句）
END

注意：
1 参数列表包含三部分
参数模式 参数名 参数类型
如：
in stuname varchar(20)

参数模式：
in  该参数可以作为输入，也就是该参数需要调用方传入值
out 该参数可以作为输出，也就是该参数可以作为返回值
inout 该参数既可以作为输入又可以作为输出，也就是该参数既需要传入值，又可以返回值

2 如果存储过程体仅仅就只有一句话，可以省略 begin end
  存储过程的结尾要求每条SQL语句必须加分号;
  存储过程的结尾可以使用 delimiter 重新设置
  DELIMITER 结束标记
  如： DELIMITER $
*/
  
#二 调用
/*
CALL 存储过程名(实参列表);
*/

#1 空参列表
-- 插入到 admin 表中5条记录
-- 要在命令行中执行
DELIMITER $
CREATE PROCEDURE myp1()
BEGIN
    INSERT INTO admin(username,`password`) VALUES('wck1','0000'),('wck2','0000'),
    ('wck3','0000'),('wck4','0000'),('wck5','0000');
END $
-- 要在命令行中调用
CALL myp1()$

#2 创建一个带in模式参数的存储过程
-- 创建存储过程实现，根据女神名，查询相对应的男神的信息
CREATE PROCEDURE myp2(IN beautyName VARCHAR(20))
BEGIN 

    SELECT bo.* FROM boys bo RIGHT JOIN beauty b 
    ON bo.id=b.boyfriend_id
    WHERE b.name=beautyName;
END $

-- 调用
CALL myp2('柳岩')$

#2 ：创建存储过程实现，用户是否登录成功

CREATE PROCEDURE myp4(IN username VARCHAR(20),IN PASSWORD VARCHAR(20))
BEGIN
	DECLARE result INT DEFAULT 0;#声明并初始化
	
	SELECT COUNT(*) INTO result#赋值
	FROM admin
	WHERE admin.username = username
	AND admin.password = PASSWORD;
	
	SELECT IF(result>0,'成功','失败');#使用
END $
#调用
CALL myp4('张飞','8888')$

#3.创建out 模式参数的存储过程
#案例1：根据输入的女神名，返回对应的男神名

CREATE PROCEDURE myp6(IN beautyName VARCHAR(20),OUT boyName VARCHAR(20))
BEGIN
	SELECT bo.boyname INTO boyname
	FROM boys bo
	RIGHT JOIN
	beauty b ON b.boyfriend_id = bo.id
	WHERE b.name=beautyName ;
	
END $

#调用
CALL myp6('许佳琪',@bname)$
SELECT @bname$


#根据输入的女神名，返回对应的男神名和魅力值

CREATE PROCEDURE myp7(IN beautyName VARCHAR(20),OUT boyName VARCHAR(20),OUT usercp INT) 
BEGIN
	SELECT boys.boyname ,boys.usercp INTO boyname,usercp
	FROM boys 
	RIGHT JOIN
	beauty b ON b.boyfriend_id = boys.id
	WHERE b.name=beautyName ;
	
END $


#调用
CALL myp7('许佳琪',@name,@cp)$
SELECT @name,@cp$


#4.创建带inout模式参数的存储过程
#传入a和b两个值，最终a和b都翻倍并返回

CREATE PROCEDURE myp8(INOUT a INT ,INOUT b INT)
BEGIN
	SET a=a*2;
	SET b=b*2;
END $


#调用
SET @m=10$
SET @n=20$
CALL myp8(@m,@n)$
SELECT @m,@n$


#二、存储过程的删除
#语法：drop procedure 存储过程名
DROP PROCEDURE myp8;

#三、查看存储过程的信息
SHOW CREATE PROCEDURE myp1;



####案例讲解
#一、创建存储过程实现传入用户名和密码，插入到admin表中

CREATE PROCEDURE test_pro1(IN username VARCHAR(20),IN loginPwd VARCHAR(20))
BEGIN
	INSERT INTO admin(admin.username,PASSWORD)
	VALUES(username,loginpwd);
END $

#二、创建存储过程实现传入女神编号，返回女神名称和女神电话

CREATE PROCEDURE test_pro2(IN id INT,OUT NAME VARCHAR(20),OUT phone VARCHAR(20))

BEGIN
	SELECT b.name ,b.phone INTO NAME,phone
	FROM beauty b
	WHERE b.id = id;

END $
#三、创建存储存储过程或函数实现传入两个女神生日，返回大小

CREATE PROCEDURE test_pro3(IN birth1 DATETIME,IN birth2 DATETIME,OUT result INT)
BEGIN
	SELECT DATEDIFF(birth1,birth2) INTO result;
END $
#四、创建存储过程或函数实现传入一个日期，格式化成xx年xx月xx日并返回
CREATE PROCEDURE test_pro4(IN mydate DATETIME,OUT strDate VARCHAR(50))
BEGIN
	SELECT DATE_FORMAT(mydate,'%y年%m月%d日') INTO strDate;
END $

CALL test_pro4(NOW(),@str)$
SELECT @str $

#五、创建存储过程或函数实现传入女神名称，返回：女神 and 男神  格式的字符串
如 传入 ：小昭
返回： 小昭 AND 张无忌
DROP PROCEDURE test_pro5 $
CREATE PROCEDURE test_pro5(IN beautyName VARCHAR(20),OUT str VARCHAR(50))
BEGIN
	SELECT CONCAT(beautyName,' and ',IFNULL(boyName,'null')) INTO str
	FROM boys bo
	RIGHT JOIN beauty b ON b.boyfriend_id = bo.id
	WHERE b.name=beautyName;
	
	
	SET str=
END $

CALL test_pro5('柳岩',@str)$
SELECT @str $



#六、创建存储过程或函数，根据传入的条目数和起始索引，查询beauty表的记录
DROP PROCEDURE test_pro6$
CREATE PROCEDURE test_pro6(IN startIndex INT,IN size INT)
BEGIN
	SELECT * FROM beauty LIMIT startIndex,size;
END $

CALL test_pro6(3,5)$


















