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

### 9.1修改字段的数据类型

```
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



### 9.2 修改字段的名字

```
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



### 9.3 同时修改字段的名字和数据类型

```
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
### 9.3 修改字段的顺序

**语法：ALTER TABLE 表名 MODIFY 属性名1  数据类型  FIRST|AFTER  属性名2**



 ## 10. 操作表约束
