## **数据库和SQL**

**DB(1)**  
**• DB： Database = Data + Base**  
**• 数据库：数据+库，存放数据的库(场所)**  
**• 数据：规范、半规范、不规范数据**  
**• 库**  
**– 一个空间，一个场所 ** 
**– 停车场、钱包、教室**  
**– 文件**  

**DB(2)**  
**• DB：保存数据的地方**  
**– 数据安全、安全、安全**  
**– 存取效率**  
**– 性价比高**  

**DB分类(1)**  
**• DB（文件集合，类似.doc,.docx文件）**  
**• DBMS: Database Management System（类似Office/WPS）**  
**– 操纵和管理数据库的软件，可建立、使用和维护数据库**  
**• DB种类**  
**– 文本文件/二进制文件**  
**– Xls文件**  
**– Access（包含在office里面，收费，只能运行在Windows上。32和64**  
**位，office 95/97/2000/2003/2007/2010/…）**  



**DB分类(2)**    

**• DB种类(续)**  
**– Mysql /Postgresql/Berkely DB (免费, 但也有收费版。多平台，**  
**32和64位区分。)**  
**– SQL Server（收费，只能运行Windows，32位和64位，中文**  
**文档。SQL Server 2000/2005/2008/2012/…,也有免费版，但**  
**有CPU和内存限制） ** 
**– Oracle/DB2（收费，全平台，32和64 位，英文文档，也有**  
**免费版，但有CPU和内存限制）**  
**– SQLite (免费,手机上使用) ** 

**表**  
**• 表：table, 实体**  
**– 列：列、属性、字段**  
**– 行：记录、元组tuple，数据 **   
**• 数据值域：数据的取值范围**  
**• 字段类型**  
**– int :整数 -2147483648~2147483647，4个字节**  
**– double：小数，8个字节 ** 
**– datetime ：时间，7个字节**  
**– varchar：字符串，可变字节 ** 

**SQL(1)**  
**• 结构化查询语言(Structured Query Language)，简称SQL**  
**– 是一种特殊目的的编程语言，是一种数据库查询和程序设计语 **   
**言，用于存取数据以及查询、更新和管理关系数据库系统；同 **   
**时也是数据库脚本文件的扩展名。**  
**• SQL标准 **  
**– SQL-86/SQL-89/SQL-92 **  
**– SQL:1999/ SQL:2003/ SQL:2008/ SQL:2011/ SQL:2016**  
**– 基础的部分，所有标准都一样**  
**– 标准仅仅是标准，每个厂商的数据库实现可能有一些不一致**   

**SQL(2)**  
**create table t1(a int, b varchar(20));**  
**insert into t1(a,b) values(1,’abc’);**  
**select a from t1; **  
**select a,b from t1 where a > 1;**  
**delete from t1 where a = 10 and b=‘ab’; **  
**update t1 set a=2, b = ‘cd’ where a=1 and b=‘ab’;**  
**drop table t1;**  

**总结**  
**• 了解数据库的基础概念**  
**• 了解SQL的语法和基本作用 ** 

