# 一、mysql的架构介绍

## 1.1 Mysql简介

### 1.1.1）高级MySQL

1. mysql内核
2. sql优化工程师
3. mysql服务器的优化
4. 查询语句优化
5. 主重复制
6. 软硬件升级
7. 容灾备份
8. sql编程

## 1.2）MysqlLinux版本的安装--mysql5.5

### 1.2.1）下载地址

下载地址:https://dev.mysql.com/downloads/mysql/

国内镜像地址：https://mirrors.tuna.tsinghua.edu.cn/mysql/downloads/MySQL-5.5/

### 1.2.2）检查当前系统是否安装过mysql/mariadb

- 可以用 `rpm -qa|grep mysql`看当前的系统进程，如果有mysql服务的就会显示的，删除已安装的和下面的类似，`rpm -e --nodeps  xxxx`
- 检查 mariadb

<img src="images/5.png" alt="5" style="zoom:80%;" />

```shell
[root@localhost opt]# rpm -qa|grep mariadb
[root@localhost opt]# rpm -e --nodeps  mariadb-libs-5.5.52-1.el7.x86_64
[root@localhost opt]# rpm -qa|grep mariadb
[root@localhost opt]# ll
```

### 1.2.3）安装mysql服务端(注意提示)

1. 将 rpm 安装包拷贝到 opt 目录下

<img src="images/1.png" alt="1" style="zoom: 80%;" />

2. 在安装目录下执行 rpm 安装

   - 安装server

   <img src="images/2.png" alt="2" style="zoom:80%;" />

   ```shell
   [root@localhost opt]# rpm -ivh MySQL-server-5.5.48-1.linux2.6.x86_64.rpm  --force --nodeps
   
   #####提示可以修改密码
   /usr/bin/mysqladmin -u root password 'new-password'
   /usr/bin/mysqladmin -u root -h localhost.localdomain password 'new-password'
   ```

   ### 1.2.3）安装mysql客户端

   ![3](images/3.png)

   ```shell
   [root@localhost opt]# rpm -ivh MySQL-client-5.5.48-1.linux2.6.x86_64.rpm --force --nodeps
   ```

   ### 1.2.4）查看Mysql安装时创建的mysql用户和mysql组

<img src="images/4.png" alt="4" style="zoom:80%;" />

```shell
[root@localhost opt]# cat /etc/passwd|grep mysql
[root@localhost opt]# cat /etc/group|grep mysql
[root@localhost opt]# mysqladmin --version
```

### 1.2.4）mysql服务的启+停

- 启动

<img src="images/6.png" alt="6" style="zoom:80%;" />

```shell
[root@localhost opt]# service mysql start
[root@localhost opt]# ps -ef | grep mysql
```

- 停止

<img src="images/7.png" alt="7" style="zoom:80%;" />

```shell
[root@localhost opt]# service mysql stop
[root@localhost opt]# ps -ef | grep mysql
```

### 1.2.5）mysql服务启动后开始连接

#### 1.2.5.1）首次连接成功

**注意这里,**因为mysql默认米有密码,所以这里我们没有输入密码就直接连上了

<img src="images/8.png" alt="8" style="zoom:80%;" />

```shell
[root@localhost opt]# mysql
```

#### 1.2.5.2）按照安装server中的提示修改登录密码

<img src="images/9.png" alt="9" style="zoom:80%;" />

```shell
[root@localhost opt]# /usr/bin/mysqladmin -u root password '123456'
[root@localhost opt]# mysql
[root@localhost opt]# mysql -uroot -p
```

### 1.2.6）自启动mysql服务

<img src="images/10.png" alt="10" style="zoom:80%;" />

```shell
[root@localhost opt]# chkconfig mysql on
[root@localhost opt]# chkconfig --list| grep mysql
```

### 1.2.7）修改配置文件位置

<img src="images/11.png" alt="11" style="zoom:80%;" />

```shell
[root@localhost mysql]# cp /usr/share/mysql/my-huge.cnf /etc/my.cnf
```

重启mysql

<img src="images/14.png" alt="14" style="zoom:80%;" />

```shell
[root@localhost mysql]# service mysql stop
[root@localhost mysql]# service mysql start
```

### 1.2.8）修改字符集

1. 准备工作

创建一个数据库，并创建一张表，插入数据，查看数据（会乱码）

```mysql
CREATE database mydb;
use mydb;
CREATE table mytable(id int,name varchar(30));
insert into mytable(id,name) values (1,'jack');
insert into mytable(id,name) values (2,'张三');
select * from mytable;
```

![12](images/12.png)

1. 查看字符集

   <img src="images/13.png" alt="13" style="zoom:80%;" />

```mysql
mysql> show variables like 'character%';
mysql> show variables like '%char%';
```

2. 修改的地方汇总（标红的地方）

![15](images/15.png)

2. 1按照上图修改的地方进行在 `vim /etc/my.cnf`文件中修改

<img src="images/16.png" alt="16" style="zoom:80%;" />

<img src="images/17.png" alt="17" style="zoom:80%;" />

```shell
[root@localhost etc]# vim /etc/my.cnf
###修改的内容为：
[client]
default-character-set=utf8
[mysqld]
character_set_server=utf8
character_set_client=utf8
collation-server=utf8_general_ci
[mysql]
default-character-set=utf8
```

2. 2重启mysql

<img src="images/14.png" alt="14" style="zoom:80%;" />

2. 3重新连接mysql后，重新`CREATE database`建库，并使用新库，建新表试试字符集

<img src="images/18.png" alt="18" style="zoom:80%;" />

```mysql
CREATE database mydb2;
use mydb2;
CREATE table mytable2(id int,name varchar(30));
insert into mytable2(id,name) values (1,'张三');
select * from mytable2;
```

备注：

- （1）修改已创建库、表字符集
  修改数据库的字符集
  `mysql> alter datbase mydb charcter set 'uf8';`
  修改数据表的字符集
  `mysql> alter table mytbl converto charcter set 'uf8';`
- （2）**修改已经乱码数据**
  **无论是修改 mysql 配置文件或是修改库、表字符集，都无法改变已经变成乱码的数据。**
  **只能删除数据重新插入或更新数据才可以完全解决**

### 1.2.9）Mysql的安装位置，在linux下查看安装目录 `ps -ef|grep mysql`

在 使用`ps -ef|grep mysql`命令后 ,可以看到   --datadir=/var/lib/mysql 数据库的存放路径

| 路径              | 解释                      | 备注                       |
| ----------------- | ------------------------- | -------------------------- |
| /var/lib/mysql/   | mysql数据库文件的存放路径 |                            |
| /usr/share/mysql  | 配置文件目录              | mysql.server命令及配置文件 |
| /usr/bin          | 相关命令目录              | mysqladmin mysqldump等命令 |
| /etc/init.d/mysql | 启停相关脚本              |                            |

![19](images/19.png)

```shell
[root@localhost mysql]# ps -ef | grep mysql
```

## 1.3）Mysql配置文件   

**主要配置文件：**

- **二进制日志log-bin**

<img src="images/20.png" alt="20" style="zoom:80%;" />

- **错误日志log-error**

  默认是关闭的,记录严重的警告和错误信息,每次启动和关闭的详细信息等.

- **查询日志log**

  默认关闭,记录查询的sql语句，如果开启会减低mysql的整体性能，因为记录日志也是需要消耗系统资源的

- **数据文件**

  - 两系统
    - windows：`D:\ProgramFiles\MySQL\MySQLServer5.5\data`目录下可以挑选很多库
    - linux：默认路径：`/var/lib/mysql`

  - frm文件：存放表结构

  - myd文件：存放表数据
  - myi文件：存放表索引

- **如何配置**
  - windows：my.ini文件
  - Linux：/etc/my.cnf文件

## 1.4）Mysql逻辑架构介绍

### 1.4.1）总体概览

和其它数据库相比，MySQL有点与众不同，它的架构可以在多种不同场景中应用并发挥良好作用。**主要体现在存储引擎的架构上，插件式的存储引擎架构将查询处理和其它的系统任务以及数据的存储提取相分离。**这种架构可以根据业务的需求和实际需要选择合适的存储引擎。

<img src="images/21.png" alt="21" style="zoom:80%;" />

1. 连接层
   最上层是一些客户端和连接服务，包含本地sock通信和大多数基于客户端/服务端工具实现的类似于tcp/ip的通信。主要完成一些类似于连接处理、授权认证、及相关的安全方案。在该层上引入了线程池的概念，为通过认证安全接入的客户端提供线程。同样在该层上可以实现基于SSL的安全链接。服务器也会为安全接入的每个客户端验证它所具有的操作权限。

2. 服务层
   第二层架构主要完成大多数的核心服务功能，如SQL接口，并完成缓存的查询，SQL的分析和优化及部分内置函数的执行。所有跨存储引擎的功能也在这一层实现，如过程、函数等。在该层，服务器会解析查询并创建相应的内部解析树，并对其完成相应的优化如确定查询表的顺序，是否利用索引等，最后生成相应的执行操作。如果是select语句，服务器还会查询内部的缓存。如果缓存空间足够大，这样在解决大量读操作的环境中能够很好的提升系统的性能。

3. 引擎层
   存储引擎层，存储引擎真正的负责了MySQL中数据的存储和提取，服务器通过APl与存储引擎进行通信。不同的存储引擎具有的功能不同，这样我们可以根据自己的实际需要进行选取。后面介绍MyISAM和InnoDB

4. 存储层
   数据存储层，主要是将数据存储在运行于裸设备的文件系统之上，并完成与存储引擎的交互。

| Connectors                       | 指的是不同语言中与SQL的交互                                  |
| -------------------------------- | ------------------------------------------------------------ |
| Management Serveices & Utilities | 系统管理和控制工具                                           |
| Connection Pool                  | 连接池管理缓冲用户连接，线程处理等需要缓存的需求。负责监听对 MySQL Server 的各种请求，接收连接请求，转发所有连接请求到线程管理模块。每一个连接上 MySQL Server 的客户端请求都会被分配（或创建）一个连接线程为其单独服务。而连接线程的主要工作就是负责 MySQL Server 与客户端的通信，接受客户端的命令请求，传递 Server 端的结果信息等。线程管理模块则负责管理维护这些连接线程。包括线程的创建，线程的 cache 等。 |
| SQL Interface                    | SQL接口。接受用户的SQL命令，并且返回用户需要查询的结果。比如select from就是调用SQL Interface |
| Parser                           | 解析器。SQL命令传递到解析器的时候会被解析器验证和解析。解析器是由Lex和YACC实现的，是一个很长的脚本。在 MySQL中我们习惯将所有 Client 端发送给 Server 端的命令都称为 query ，在 MySQL Server 里面，连接线程接收到客户端的一个 Query 后，会直接将该 query 传递给专门负责将各种 Query 进行分类然后转发给各个对应的处理模块。主要功能：<br/> a . 将SQL语句进行语义和语法的分析，分解成数据结构，然后按照不同的操作类型进行分类，然后做出针对性的转发到后续步骤，以后SQL语句的传递和处理就是基于这个结构的。<br/> b.  如果在分解构成中遇到错误，那么就说明这个sql语句是不合理的 |
| Optimizer                        | 查询优化器。SQL语句在查询之前会使用查询优化器对查询进行优化。就是优化客户端请求的 query（sql语句） ，根据客户端请求的 query 语句，和数据库中的一些统计信息，在一系列算法的基础上进行分析，得出一个最优的策略，告诉后面的程序如何取得这个 query 语句的结果他使用的是“选取-投影-联接”策略进行查询。 <br/> 用一个例子就可以理解： select uid,name from user where gender = 1;       这个select 查询先根据where 语句进行选取，而不是先将表全部查询出来以后再进行gender过滤       这个select查询先根据uid和name进行属性投影，而不是将属性全部取出以后再进行过滤       将这两个查询条件联接起来生成最终查询结果 |
| Cache和Buffer                    | 查询缓存。他的主要功能是将客户端提交 给MySQL 的 Select 类 query 请求的返回结果集 cache 到内存中，与该 query 的一个 hash 值 做一个对应。该 Query 所取数据的基表发生任何数据的变化之后， MySQL 会自动使该 query 的Cache 失效。在读写比例非常高的应用系统中， Query Cache 对性能的提高是非常显著的。当然它对内存的消耗也是非常大的。如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取数据。这个缓存机制是由一系列小缓存组成的。比如表缓存，记录缓存，key缓存，权限缓存等 |
| 优点                             | 存储引擎接口存储引擎接口模块可以说是 MySQL 数据库中最有特色的一点了。目前各种数据库产品中，基本上只有 MySQL 可以实现其底层数据存储引擎的插件式管理。这个模块实际上只是 一个抽象类，但正是因为它成功地将各种数据处理高度抽象化，才成就了今天 MySQL 可插拔存储引擎的特色。<br/>从图2还可以看出，MySQL区别于其他数据库的最重要的特点就是其插件式的表存储引擎。MySQL插件式的存储引擎架构提供了一系列标准的管理和服务支持，这些标准与存储引擎本身无关，可能是每个数据库系统本身都必需的，如SQL分析器和优化器等，而存储引擎是底层物理结构的实现，每个存储引擎开发者都可以按照自己的意愿来进行开发。    注意：存储引擎是基于表的，而不是数据库。 |

## 1.5）Mysql存储引擎

### 1.5.1）查看命令

查看mysql支持的引擎

<img src="images/23.png" alt="23" style="zoom:80%;" />

查看mysql现在使用的是什么引擎

<img src="images/22.png" alt="22" style="zoom:80%;" />

```mysql
mysql> show engines;
mysql> show variables like '%storage_engine%';
```

### 1.5.2）MyISAM和InnoDB

| 对比项   | MyISAM                                                   | InnoDB不支持                                                 |
| -------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 主外键   | 不支持                                                   | 支持                                                         |
| 事务     | 不支持                                                   | 支持                                                         |
| 行表锁   | 表锁，即使操作一条记录也会锁住整个表，不适合高并发的操作 | 行锁，操作时只锁某一行，不对其他行有影响，适合高并发操作     |
| 缓存     | 只缓存索引，不缓存真实数据                               | 不仅缓存索引还要缓存真实数据，对内存要求较高，而且内存大小对性能有决定性的影响 |
| 表空间   | 小                                                       | 大                                                           |
| 关注点   | 性能                                                     | 事务                                                         |
| 默认安装 | Y                                                        | Y                                                            |

# 二、索引优化分析

## 2.1）性能下降SQL慢 -执行时间长 -等待时间长

- 查询语句写的烂
- 索引失效
  - 单值
  - 复合

- 关联查询太多join(设计缺陷或不得已的需求)
- 服务器调优及各个参数设置(缓冲\线程数等)

## 2.2）常见通用的join查询

### 2.2.1）SQL执行顺序

- 手写

```mysql
SELECT DISTINCT
<select_list>
FROM
<left_table><join_type>
JOIN<right_table>ON<join_condition>
WHERE
<where_condition>
GROUP BY
<group_by_list>
HAVING
<having_condition>
ORDER BY
<order_by_condition>
LIMIT<limit_number>
```

- 机读

```mysql
FROM <left_table>
ON <join_condition>
<join_type> JOIN <right_table>
WHERE <where_condition>
GROUP BY <group_by_list>
HAVING <having_condition>
SELECT 
DISTINCT <select_list>
ORDER BY <order_by_condition>
LIMIT<limit_number>
```

- 总结

<img src="images/24.png" alt="24" style="zoom:80%;" />

### 2.2.2）Join图

![25](images/25.png)

### 2.2.3）建表SQL

```mysql
CREATE TABLE `tbl_dept` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`deptName` VARCHAR(30) DEFAULT NULL,
`locAdd` VARCHAR(40) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_emp` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(20) DEFAULT NULL,
`deptId` INT(11) DEFAULT NULL, 
PRIMARY KEY (`id`),
KEY `fk_dept_id` (`deptId`)
#CONSTRAINT `fk_dept_jid` FOREIGN KEY (`deptId`) REFERENCES `tbl_dept` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO tbl_dept(deptName,locAdd) VALUES('RD',11);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('HR',12);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('MK',13);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('MIS',14);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('FD',15);

INSERT INTO tbl_emp(NAME ,deptId) VALUES('z3',1);
INSERT INTO tbl_emp(NAME,deptId) VALUES('z4',1);
INSERT INTO tbl_emp(NAME ,deptId) VALUES('z5',1);
INSERT INTO tbl_emp(NAME ,deptId) VALUES('w5',2);
INSERT INTO tbl_emp(NAME,deptId) VALUES('w6',2);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s7',3);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s8',4);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s9',51 );


INSERT INTO tbl_dept(deptName,locAdd) VALUES('BSR',250);
INSERT INTO tbl_emp(NAME,deptId) VALUES('wgk',999);
```

### 2.2.4）7种Join

**准备工作**

```mysql
mysql> select * from tbl_emp;
+----+------+--------+
| id | name | deptId |
+----+------+--------+
|  1 | z3   |      1 |
|  2 | z4   |      1 |
|  3 | z5   |      1 |
|  4 | w5   |      2 |
|  5 | w6   |      2 |
|  6 | s7   |      3 |
|  7 | s8   |      4 |
|  8 | s9   |     51 |
+----+------+--------+
8 rows in set (0.00 sec)
mysql> select * from tbl_dept;
+----+----------+--------+
| id | deptName | locAdd |
+----+----------+--------+
|  1 | RD       | 11     |
|  2 | HR       | 12     |
|  3 | MK       | 13     |
|  4 | MIS      | 14     |
|  5 | FD       | 15     |
+----+----------+--------+
5 rows in set (0.00 sec)
```



1. 内连接`inner join` 

```mysql
mysql> select * from tbl_emp a inner join tbl_dept b on a.deptId=b.id;
+----+------+--------+----+----------+--------+
| id | name | deptId | id | deptName | locAdd |
+----+------+--------+----+----------+--------+
|  1 | z3   |      1 |  1 | RD       | 11     |
|  2 | z4   |      1 |  1 | RD       | 11     |
|  3 | z5   |      1 |  1 | RD       | 11     |
|  4 | w5   |      2 |  2 | HR       | 12     |
|  5 | w6   |      2 |  2 | HR       | 12     |
|  6 | s7   |      3 |  3 | MK       | 13     |
|  7 | s8   |      4 |  4 | MIS      | 14     |
+----+------+--------+----+----------+--------+
7 rows in set (0.00 sec)
```

2. 左连接 `left join`

```mysql
mysql> select * from tbl_emp a left join tbl_dept b on a.deptId=b.id;
+----+------+--------+------+----------+--------+
| id | name | deptId | id   | deptName | locAdd |
+----+------+--------+------+----------+--------+
|  1 | z3   |      1 |    1 | RD       | 11     |
|  2 | z4   |      1 |    1 | RD       | 11     |
|  3 | z5   |      1 |    1 | RD       | 11     |
|  4 | w5   |      2 |    2 | HR       | 12     |
|  5 | w6   |      2 |    2 | HR       | 12     |
|  6 | s7   |      3 |    3 | MK       | 13     |
|  7 | s8   |      4 |    4 | MIS      | 14     |
|  8 | s9   |     51 | NULL | NULL     | NULL   |
+----+------+--------+------+----------+--------+
8 rows in set (0.00 sec)
```

3. 右连接 `right join`

```mysql
mysql> select * from tbl_emp a right join tbl_dept b on a.deptId=b.id;
+------+------+--------+----+----------+--------+
| id   | name | deptId | id | deptName | locAdd |
+------+------+--------+----+----------+--------+
|    1 | z3   |      1 |  1 | RD       | 11     |
|    2 | z4   |      1 |  1 | RD       | 11     |
|    3 | z5   |      1 |  1 | RD       | 11     |
|    4 | w5   |      2 |  2 | HR       | 12     |
|    5 | w6   |      2 |  2 | HR       | 12     |
|    6 | s7   |      3 |  3 | MK       | 13     |
|    7 | s8   |      4 |  4 | MIS      | 14     |
| NULL | NULL |   NULL |  5 | FD       | 15     |
+------+------+--------+----+----------+--------+
8 rows in set (0.00 sec)
```

4. a 表中独享的数据

```mysql
mysql> select * from tbl_emp a left join tbl_dept b on a.deptId=b.id where b.id is null;
+----+------+--------+------+----------+--------+
| id | name | deptId | id   | deptName | locAdd |
+----+------+--------+------+----------+--------+
|  8 | s9   |     51 | NULL | NULL     | NULL   |
+----+------+--------+------+----------+--------+
1 row in set (0.00 sec)
```

5. b 表中独享的数据

```mysql
mysql> select * from tbl_emp a right join tbl_dept b on a.deptId=b.id where a.deptId is null;
+------+------+--------+----+----------+--------+
| id   | name | deptId | id | deptName | locAdd |
+------+------+--------+----+----------+--------+
| NULL | NULL |   NULL |  5 | FD       | 15     |
+------+------+--------+----+----------+--------+
1 row in set (0.00 sec)
```

6. `full outer join` MySql不支持，可以使用下面的替代倒数第二种情况

```mysql
mysql> select * from tbl_emp a left join tbl_dept b on a.deptId=b.id
    -> union
    -> select * from tbl_emp a right join tbl_dept b on a.deptId=b.id;
+------+------+--------+------+----------+--------+
| id   | name | deptId | id   | deptName | locAdd |
+------+------+--------+------+----------+--------+
|    1 | z3   |      1 |    1 | RD       | 11     |
|    2 | z4   |      1 |    1 | RD       | 11     |
|    3 | z5   |      1 |    1 | RD       | 11     |
|    4 | w5   |      2 |    2 | HR       | 12     |
|    5 | w6   |      2 |    2 | HR       | 12     |
|    6 | s7   |      3 |    3 | MK       | 13     |
|    7 | s8   |      4 |    4 | MIS      | 14     |
|    8 | s9   |     51 | NULL | NULL     | NULL   |
| NULL | NULL |   NULL |    5 | FD       | 15     |
+------+------+--------+------+----------+--------+
9 rows in set (0.00 sec)
```

7. `full outer join` MySql不支持，可以使用下面的替代倒数第一种情况

```mysql
mysql> select * from tbl_emp a left join tbl_dept b on a.deptId=b.id where b.id is null 
    -> union
    -> select * from tbl_emp a right join tbl_dept b on a.deptId=b.id where a.deptId is null;
+------+------+--------+------+----------+--------+
| id   | name | deptId | id   | deptName | locAdd |
+------+------+--------+------+----------+--------+
|    8 | s9   |     51 | NULL | NULL     | NULL   |
| NULL | NULL |   NULL |    5 | FD       | 15     |
+------+------+--------+------+----------+--------+
2 rows in set (0.00 sec)
```













