```mysql
create table t_dept(
deptno int PRIMARY KEY AUTO_INCREMENT ,
dname varchar(20) ,
loc varchar(40)
);

create table t_dept(
deptno int PRIMARY KEY ,
dname varchar(20) ,
loc varchar(40),
);



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

