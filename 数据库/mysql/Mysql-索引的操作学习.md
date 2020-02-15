# MySQL

## 1.为什么使用索引

​        数据库对象索引其实与书的目录非常类似，主要是为了提高从表中检索数据的速度。由于数据存储在数据库表中，所以索引是创建在数据库表对象上的，由表中的一个字段或多个字段生成的键组成，这些键存储在数据结构（B-树或哈希表）中，通过MySQL可以快速有效地查找与键值相关联的字段。根据索引的存储类型，可以将索引分为B型树索引（BTREE)和哈希索引（HASH）。

**注意**：InnoDB和MyISAM存储引学支持BTREE类型索引，MEMORY存储引季支持HASH类型索引，默认为前者索引。

数据库对象索引的出现，除了可以提高数据库管理系统的查找速度，而且还可以保证字段的唯一性，从而实现数据库表的完整性。查看帮助文档，可以发现MySQL支持6种索引，它们分别为普通索引、唯一索引、全文索引、单列索引、多列索引和空间索引。
索引的创建有利有弊，创建索引可以提高查询速度，但过多的创建索引则会占据许多的磁盘空间。因此在创建索引之前，必须权衡利弊。
以下情况下适合创建索引：
1.经常被查询的字段，即在WHERE子句中出现的字段。
2.在分组的字段，即在GROUPBY子句中出现的字段。
3.存在依赖关系的子表和父表之间的联合查询，即主键或外键字段。

4.设置唯一完整性约束的字段。  

以下情况下,不适合创建索引:  

1.在查询中很少被使用的字段。  

2.拥有许多重复值的字段。



## 2.创建和查看普通索引

#### 2.1创建表时创建普通索引

```mysql
mysql> create table t_dept(
    -> deptno int,
    -> dname varchar(20) ,
    -> loc varchar(40),
    -> index index_deptno(deptno)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> show create table t_dept\G;
*************************** 1. row ***************************
       Table: t_dept
Create Table: CREATE TABLE `t_dept` (
  `deptno` int(11) DEFAULT NULL,
  `dname` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `loc` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  KEY `index_deptno` (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
1 row in set (0.00 sec)

ERROR:
No query specified

mysql>

```

校验数据库表t_dept 中索引 是否被使用 执行sql语句 explain 

   --执行结果显示,由于possible keys和key字段处的值都为所创建的索引名 index_deptno,则说明该索引已经存在,而且已经开始启用。

```mysql
mysql> explain select * from t_dept where deptno=1\G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t_dept
   partitions: NULL
         type: ref
possible_keys: index_deptno
          key: index_deptno
      key_len: 5
          ref: const
         rows: 1
     filtered: 100.00
        Extra: NULL
1 row in set, 1 warning (0.00 sec)

ERROR:
No query specified

mysql>
```

