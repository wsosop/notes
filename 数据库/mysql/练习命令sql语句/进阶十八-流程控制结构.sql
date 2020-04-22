#进阶十八-流程控制结构

/**
顺序结构：程序从上往下依次执行
分之结构：程序从两条或多条路径中选择一条去执行
循环结构：程序在满足一定条件的基础上，重复执行一段代码
*/

#一、分支结构
# 1 if函数
/**
功能：能够简单的双分支

语法：
if (表达式1 ,表达式2,表达式3)
指定顺序：
如果表达式成立，则if函数返回表达式2的值，否则返回 表达式3的值
应用：任何地方   
*/

#2.case结构
/*
语法：
情况1：类似于java中的switch语句，一般用于实现等值判断
语法：
case 变量|表达式|字段
when 要判断的值1 then 返回的值1或语句1;
when 要判断的值2 then 返回的值2或语句2;
...
else 要返回的值n或语句n;
end 【case】 语句中使用要加case

情况2：类似于java中的多重if语句，一般用于实现区间判断
语法：
case 
when 要判断的条件1 then 返回的值1或语句1;
when 要判断的条件2 then 返回的值1或语句1;
...
else 要返回的值n或语句n;
end  语句中使用要加case

应用在begin end 中或外面

特点：
1）可以作为表达式，嵌套在其他语句中使用，可以放在任何地方，begin end 中或 begin end 的外面
   可以作为独立的语句去使用，只能放在begin end中
2）如果when 中的值满足条件或条件成立，则执行对应then后面的语句，并且结束case  
   如果都不满足则执行else 中的语句或值
3）else可以省略，如果else 省略了，并且所有的when条件都不满足，则返回null
*/

#例1：创建存储过程，实现传入成绩，如果成绩>90,返回A，如果成绩>80,返回B，如果成绩>60,返回C，否则返回D
CREATE PROCEDURE test_case(IN score INT)
BEGIN
    CASE
    WHEN score >=90 AND score <=100 THEN SELECT 'A';
    WHEN score >=80  THEN SELECT 'B';
    WHEN score >=60  THEN SELECT 'C';
    ELSE SELECT 'D';
    END CASE;
    
END $


CALL test_case(95)$


#3 if结构
/**
功能实现多重分支

语法：
if 条件1 then 语句1;
elseif 条件2 then 语句2;
...
【else 语句n;】
end if;

只能应用在 begin end 中

*/
#例1：创建函数，实现传入成绩，如果成绩>90,返回A，如果成绩>80,返回B，如果成绩>60,返回C，否则返回D
CREATE FUNCTION test_if(score INT) RETURNS CHAR
BEGIN 
    IF score >=90 AND score<=100 THEN RETURN 'A';
    ELSEIF score >=80  THEN RETURN 'B';
    ELSEIF score >=60  THEN RETURN 'C';
    ELSE RETURN 'C';
    END IF;
END $

SELECT test_if(50)$


#二、循环结构
/**
WHILE 
LOOP 
REPEAT

循环控制
iterate 类似于 java 中的continue ，继续，结束本次循环，继续下一次
leave 类似于 java中的 braeak ，跳出，结束当前所在的循环
*/
#1 while

/**
语法：
【标签:】while 循环条件 do
      循环体;
end while【 标签】;    
*/

#2 loop 
/**
语法：
【标签:】loop
    循环体;
 end loop 【标签】;
 
 可以用来模拟简单的死循环   
*/

#3 REPEAT
/**
语法：
【标签:】repeat 
        循环体;
 until 结束循环的条件
 end REPEAT 【标签】;
*/
# 例子： 批量插入，根据次数插入到admin 表中多条记录
-- 没有添加循环控制语句
DROP PROCEDURE pro_while1$
 CREATE PROCEDURE pro_while1(IN insertCount INT)
 BEGIN
    DECLARE i INT DEFAULT  1;
    WHILE i<=insertCount DO
        INSERT INTO admin (username,`password`) VALUES (CONCAT('rose',i),'666');  
        SET i=i+1;
        END WHILE ;
 END $

CALL pro_while1(100)$
 
TRUNCATE TABLE admin;  
SELECT * FROM admin;

# 例子： 批量插入，根据次数插入到admin 表中多条记录
-- 添加循环控制leave语句
DROP PROCEDURE pro_while1$
TRUNCATE TABLE admin;  
SELECT * FROM admin;

 CREATE PROCEDURE pro_while1(IN insertCount INT)
 BEGIN
    DECLARE i INT DEFAULT  1;
    a:WHILE i<=insertCount DO
        INSERT INTO admin (username,`password`) VALUES (CONCAT('mick',i),'666');  
            IF i>=20 THEN LEAVE a;
            END IF;
            SET i=i+1;
        END WHILE a;
 END $
 
 CALL pro_while1(100)$
 
 # 例子： 批量插入，根据次数插入到admin 表中多条记录
-- 添加循环控制iterate语句

DROP PROCEDURE pro_while1$
TRUNCATE TABLE admin;  
SELECT * FROM admin;

 CREATE PROCEDURE pro_while1(IN insertCount INT)
 BEGIN
    DECLARE i INT DEFAULT  0;
    a:WHILE i<=insertCount DO
        SET i=i+1;
        IF MOD(i,2)!=0 THEN ITERATE a;
        END IF;
        INSERT INTO admin (username,`password`) VALUES (CONCAT('mick',i),'666');  
            
        END WHILE a;
 END $
 
 CALL pro_while1(100)$

/*

int i=0;
while(i<=insertCount){
	i++;
	if(i%2==0){
		continue;
	}
	插入
}

*/

-- ------------经典例子
/*一、已知表stringcontent
其中字段：
id 自增长
content varchar(20)

向该表插入指定个数的，随机的字符串
*/