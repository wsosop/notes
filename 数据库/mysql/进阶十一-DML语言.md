# 进阶十一-DML语言

```mysql
#进阶十 DML语言
/**
数据操作语言：
插入：insert
修改：update
删除：delete

*/

#一、插入语句
/**
方式一 ：经典的插入
insert into 表名（列名,...）values(值1,...);
*/
#1 插入的值的类型要与列的类型一致或兼容
INSERT INTO beauty (id,NAME,sex,borndate,phone,photo,boyfriend_id)
VALUES(13,'唐艺昕','女','2020-01-15','156089945',NULL,2);
-- select * from beauty;

#2 不可以为null的值必须插入值，可以为null的列如何插入值？
# 方式1
INSERT INTO beauty (id,NAME,sex,borndate,phone,photo,boyfriend_id)
VALUES(13,'唐艺昕','女','2020-01-15','156089945',NULL,2);
#方式2
INSERT INTO beauty (id,NAME,sex,borndate,phone,boyfriend_id)
VALUES(14,'许佳琪','女','2020-01-15','126089945',8);

#3 列的顺序是可以颠倒的
INSERT INTO beauty (NAME,id,phone)
VALUES('wck',15,'156165454');

#4 可以省略列名，默认是所有列，而且列的顺序和表中的顺序是一致的  
INSERT INTO beauty 
VALUES(16,'唐艺昕2','女','2020-01-15','156089945',NULL,2);

#二、方式二
/**
语法：
insert into 表名
set 列名=值，列名=值，...
*/
INSERT INTO beauty 
SET id=17,NAME='wck2',phone='15600000000';

#两种方式对比
#1 方式一 支持插入多行,方式二不支持
INSERT INTO beauty (id,NAME,sex,borndate,phone,photo,boyfriend_id)
VALUES(18,'唐艺昕3','女','2020-01-15','156089945',NULL,2),
(19,'唐艺昕4','女','2020-01-15','156089945',NULL,2),
(20,'唐艺昕5','女','2020-01-15','156089945',NULL,2);

#2 方式一支持子查询，方式二不支持
INSERT INTO beauty (id,NAME,phone)
SELECT 21,'tangtang',1606060564;

#二、修改数据
/**
1.修改单表的记录
语法：
    update 表名
    set 列=新值,列2=新值2,...
    where 筛选条件;
    
    

2.修改多表的记录【补充】

语法：
    sql92语法：
    update 表1 别名,表2 别名
    set 列=值,...
    where 连接条件
    and 筛选条件;

sql99语法：
    update 表1 别名
    inner|left|right join 表2 别名
    on 连接条件
    set 列=值,...
    where 筛选条件;

*/

#1).修改单表的记录
#1 把beauty表中的 姓名带有 唐 的电话都改成110
UPDATE beauty SET phone='110' WHERE NAME LIKE '%唐%';

#2 把beauty表中的 姓名带有 唐 的电话都改成110,并且 出生年月 改成 2020-04-15
UPDATE beauty SET phone='110' ,borndate='2020-04-15' WHERE NAME LIKE '%唐%';

#2).修改多表的记录【补充】
#1 修改张无忌的女朋友的手机号为 114
UPDATE boys bo INNER JOIN beauty b ON bo.id=b.boyfriend_id
SET b.phone='114'
WHERE bo.boyName='张无忌';

#2 修改没有男朋友的女神的男朋友的编号都为2
UPDATE beauty b LEFT JOIN boys bo
ON b.boyfriend_id=bo.id
SET b.boyfriend_id=2
WHERE bo.id  IS NULL

#三、删除语句
/**
方式一：
1 单表的删除：
语法：
    delete from 表名 where 筛选条件
    
2 多表的删除【补充】
语法：
    sql92语法：
    delete 表1的别名,表2的别名
    from 表1 别名,表2 别名
    where 连接条件
    and 筛选条件;

    sql99语法：

    delete 表1的别名,表2的别名
    from 表1 别名
    inner|left|right join 表2 别名 on 连接条件
    where 筛选条件;

方式二 truncate
语法：   truncate table 表名;
   
*/

#方式一：delete
#1.单表的删除
#案例：删除手机号以9结尾的女神信息

DELETE FROM beauty WHERE phone LIKE '%9';
SELECT * FROM beauty;

#2.多表的删除

#1 删除张无忌的女朋友的信息

DELETE b FROM beauty b JOIN boys bo 
ON b.boyfriend_id=bo.id
WHERE bo.boyName='张无忌'

#2 删除黄晓明的信息，以及他女朋友的信息
DELETE b,bo FROM beauty b JOIN boys bo 
ON b.boyfriend_id=bo.id
WHERE bo.boyName='黄晓明'


#方式二：truncate语句

# 1 清空 boys 表 这个命令是清空 表的数据
TRUNCATE TABLE boys ;

#delete pk truncate【面试题★】

/*

1.delete 可以加where 条件，truncate不能加

2.truncate删除，效率高一丢丢
3.假如要删除的表中有自增长列，
如果用delete删除后，再插入数据，自增长列的值从断点开始，
而truncate删除后，再插入数据，自增长列的值从1开始。
4.truncate删除没有返回值，delete删除有返回值

5.truncate删除不能回滚，delete删除可以回滚.

*/

SELECT * FROM boys;

DELETE FROM boys;
TRUNCATE TABLE boys;
INSERT INTO boys (boyname,usercp)
VALUES('张飞',100),('刘备',100),('关云长',100);

```

