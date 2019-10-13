# MySQL

------

## 1. 查看mysql所支持的引擎

使用命令： SHOW ENGINES;

```mysql
mysql> show engines \G;
*************************** 1. row ***************************
      Engine: InnoDB
     Support: YES
     Comment: Supports transactions, row-level locking, and foreign keys
Transactions: YES
          XA: YES
  Savepoints: YES
*************************** 2. row ***************************
      Engine: MRG_MYISAM
     Support: YES
     Comment: Collection of identical MyISAM tables
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 3. row ***************************
      Engine: MEMORY
     Support: YES
     Comment: Hash based, stored in memory, useful for temporary tables
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 4. row ***************************
      Engine: BLACKHOLE
     Support: YES
     Comment: /dev/null storage engine (anything you write to it disappears)
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 5. row ***************************
      Engine: MyISAM
     Support: DEFAULT
     Comment: MyISAM storage engine
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 6. row ***************************
      Engine: CSV
     Support: YES
     Comment: CSV storage engine
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 7. row ***************************
      Engine: ARCHIVE
     Support: YES
     Comment: Archive storage engine
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 8. row ***************************
      Engine: PERFORMANCE_SCHEMA
     Support: YES
     Comment: Performance Schema
Transactions: NO
          XA: NO
  Savepoints: NO
*************************** 9. row ***************************
      Engine: FEDERATED
     Support: NO
     Comment: Federated MySQL storage engine
Transactions: NULL
          XA: NULL
  Savepoints: NULL
9 rows in set (0.00 sec)

ERROR:
No query specified

mysql>
```

注：执行结果显示，MySQL5.7.14支持9种存储引擎，分别为FEDERATED、MRG_MYISAM、MyISAM、BLACKHOLE、CSV、MEMORY、ARCHIVE、InnoDB和PERFORMANCE SCHEMA。

其中Engine参数表示存储引擎名称；

Support 参数表示MySQL数据库管理系统是否支持该存储引擎，其中值YES表示支持，值NO表示不支持，值DEFAULT表示该存储引擎是数据库管理系统默认支持的存储引擎；

Comment参数表示关于存储引擎的评论；

Transactions参数表示存储引擎是否支持事务，其中值YES表示支持，而值NO表示不支持；

XA参数表示存储引擎所支持的分布式是否符合XA规范，其中值YES表示支持，而值NO表示不支持；

Savepoints参数表示存储引擎是否支持事务处理中的保存点，其中值YES表示支持，而值NO表示不支持。


## 2. 查看表的ddl


```mysql
mysql> show create table t_dept\G;
*************************** 1. row ***************************
       Table: t_dept
Create Table: CREATE TABLE `t_dept` (
  `deptno` int(11) DEFAULT NULL,
  `dname` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `loc` varchar(40) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8
1 row in set (0.00 sec)
```

 ## 3.查看表结构

   ```mysql
   mysql> describe t_dept;
   +--------+-------------+------+-----+---------+-------+
   | Field  | Type        | Null | Key | Default | Extra |
   +--------+-------------+------+-----+---------+-------+
   | deptno | int(11)     | YES  |     | NULL    |       |
   | dname  | varchar(20) | YES  |     | NULL    |       |
   | loc    | varchar(40) | YES  |     | NULL    |       |
   +--------+-------------+------+-----+---------+-------+
   3 rows in set (0.00 sec)
   
   mysql>
   ```
 ## 4.修改表名


```mysql
mysql> alter table t_dept rename tab_dept;
Query OK, 0 rows affected (0.01 sec)

mysql> show tables;
+-------------------+
| Tables_in_company |
+-------------------+
| tab_dept          |
+-------------------+
1 row in set (0.00 sec)

mysql>

```
 ## 5. 在表的最后一个位置增加一个字段

```mysql
mysql> alter table tab_dept add descri varchar(20);
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(11)     | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
| descri | varchar(20) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql>
```
 ## 6. 在表的第一个位置增加一个字段

```mysql
mysql> alter table tab_dept add descri varchar(20) first;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| descri | varchar(20) | YES  |     | NULL    |       |
| deptno | int(11)     | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql>
```
 ## 7. 在表的第指定位置后增加一个字段

```mysql
mysql> alter table tab_dept add descri varchar(20) after deptno;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(11)     | YES  |     | NULL    |       |
| descri | varchar(20) | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql>
```

 ## 8. 删除表中的字段

```mysql
mysql> alter table tab_dept drop descri;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(11)     | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql>
```

 ## 9. 修改字段

#### 9.1修改字段的数据类型

```mysql
mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(11)     | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql> alter table tab_dept modify deptno varchar(20);
Query OK, 0 rows affected (0.04 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | varchar(20) | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql>
```



#### 9.2 修改字段的名字

```mysql
mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | varchar(20) | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql> alter table tab_dept change loc location varchar(40);
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| deptno   | varchar(20) | YES  |     | NULL    |       |
| dname    | varchar(20) | YES  |     | NULL    |       |
| location | varchar(40) | YES  |     | NULL    |       |
+----------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql>
```



#### 9.3 同时修改字段的名字和数据类型

```mysql
mysql> desc tab_dept;
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| deptno   | varchar(20) | YES  |     | NULL    |       |
| dname    | varchar(20) | YES  |     | NULL    |       |
| location | varchar(40) | YES  |     | NULL    |       |
+----------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql> alter table tab_dept change location loc varchar(20);
Query OK, 0 rows affected (0.04 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc tab_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | varchar(20) | YES  |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(20) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql>
```
#### 9.3 修改字段的顺序

**语法：ALTER TABLE 表名 MODIFY 属性名1  数据类型  FIRST|AFTER  属性名2**



 ## 10. 操作表约束

**MySQL软件所支持的完整性约束**

| 完整性约束关键字 | 含义                                          |
| ---------------- | --------------------------------------------- |
| NOT NULL         | 约束字段的值不能为空                          |
| DEFAULT          | 设置字段的默认值                              |
| UNIQUE KEY (UK)  | 约束字段的值是唯一                            |
| PRIMARY KEY(PK)  | 约束字段为表的主键,可以作为该表记录的唯一标识 |
| AUTO INCREMENT   | 约束字段的值为自动增加                        |
| FOREIGN KEY (FK) | 约束字段为表的外键                            |

注：      表中显示的完整性约束中, MySQL数据库管理系统不支持 check约束,即可以使用 check约束但是却没有任何效果。根据约束数据列限制,约束可分为:单列约束,即每个约束只约束一列数据:多列约束,即每个约束可以约束多列数据。



#### 10.1 设置非空约束（NOT NULL ,NK）

```mysql
mysql>  create table t_dept(
    -> deptno int(20) not null,
    -> dname varchar(20),
    -> loc varchar(40)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> show tables;
+-------------------+
| Tables_in_company |
+-------------------+
| t_dept            |
+-------------------+
1 row in set (0.00 sec)

mysql> desc t_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(20)     | NO   |     | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

mysql>
```

 



#### 10.2 设置字段的默认值（DEFAULT）

```mysql

mysql>  create table t_dept(
    -> deptno int(20) not null,
    -> dname varchar(20) default 'wck',
    -> loc varchar(40)
    -> );
Query OK, 0 rows affected (0.01 sec)

mysql> desc t_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(20)     | NO   |     | NULL    |       |
| dname  | varchar(20) | YES  |     | wck     |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql>
```



#### 10.3设置唯一约束（UNIQUE,UK）

```mysql

mysql> create table t_dept(
    -> deptno int(20) not null,
    -> dname varchar(20) UNIQUE,
    -> loc varchar(40)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> desc t_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(20)     | NO   |     | NULL    |       |
| dname  | varchar(20) | YES  | UNI | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql>
```

#### 10.4设置主键约束（PRIMARY KEY ,PK）

##### 10.4.1设置单字段主键约束

```mysql

mysql> create table t_dept(
    -> deptno int(20) PRIMARY KEY,
    -> dname varchar(20) ,
    -> loc varchar(40)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> DESC t_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(20)     | NO   | PRI | NULL    |       |
| dname  | varchar(20) | YES  |     | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql>
```

##### 10.4.2设置多字段主键约束

```
mysql> create table t_dept(
    -> deptno int,
    -> dname varchar(20) ,
    -> loc varchar(40),
    -> constraint pk_name_deptno PRIMARY KEY(deptno,dname)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> desc t_dept;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| deptno | int(11)     | NO   | PRI | NULL    |       |
| dname  | varchar(20) | NO   | PRI | NULL    |       |
| loc    | varchar(40) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql>
```

##### 10.4.3设置字段值自动增加（AUTO_INCREMENT）

```mysql
mysql> create table t_dept(
    -> deptno int PRIMARY KEY AUTO_INCREMENT ,
    -> dname varchar(20) ,
    -> loc varchar(40)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> desc t_dept;
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| deptno | int(11)     | NO   | PRI | NULL    | auto_increment |
| dname  | varchar(20) | YES  |     | NULL    |                |
| loc    | varchar(40) | YES  |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+
3 rows in set (0.01 sec)

mysql>
```

10.4.4设置外键约束（FOREIGN KEY ,FK）

```mysql
mysql> desc t_dept;
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| deptno | int(11)     | NO   | PRI | NULL    | auto_increment |
| dname  | varchar(20) | YES  |     | NULL    |                |
| loc    | varchar(40) | YES  |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+
3 rows in set (0.01 sec)

mysql> create table t_employee(
    -> empno int primary key,
    -> ename varchar(20),
    -> job varchar(40),
    -> MGR int,
    -> Hiredate date,
    -> sal double(10,2),
    -> comm double(10,2),
    -> deptno int,
    -> constraint fk_deptno foreign key(deptno)
    -> references t_dept(deptno)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> desc t_employee;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| empno    | int(11)      | NO   | PRI | NULL    |       |
| ename    | varchar(20)  | YES  |     | NULL    |       |
| job      | varchar(40)  | YES  |     | NULL    |       |
| MGR      | int(11)      | YES  |     | NULL    |       |
| Hiredate | date         | YES  |     | NULL    |       |
| sal      | double(10,2) | YES  |     | NULL    |       |
| comm     | double(10,2) | YES  |     | NULL    |       |
| deptno   | int(11)      | YES  | MUL | NULL    |       |
+----------+--------------+------+-----+---------+-------+
8 rows in set (0.01 sec)

mysql>
```

