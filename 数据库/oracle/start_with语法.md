## start_with语法

sql 语句：

```java
CREATE TABLE "TAB_TEST" 
   ("ID" NUMBER, 
	"FID" NUMBER, 
	"REMARK" VARCHAR2(255)
   )
```

表中数据为：

| ID   | FID  | REMARK      |
| ---- | ---- | ----------- |
| 0    | -1   | 根节点      |
| 1    | 0    | 第二层节点1 |
| 2    | 0    | 第二层节点2 |
| 3    | 1    | 第三层节点1 |
| 4    | 1    | 第三层节点2 |



### oracle connect by用法

一般用来查找存在父子关系的数据，也就是树形结构的数据；其返还的数据也能够明确的区分出每一层的数据。

- start with condition1 是用来限制第一层的数据，或者叫根节点数据；以这部分数据为基础来查找第二层数据，然后以第二层数据查找第三层数据以此类推。
- connect by [prior] **id=parentid** 这部分是用来指明oracle在查找数据时以怎样的一种关系去查找；比如说查找第二层的数据时用第一层数据的**id**去跟表里面记录的**parentid**字段进行匹配，如果这个条件成立那么查找出来的数据就是第二层数据，同理查找第三层第四层…等等都是按这样去匹配。
- prior 在那边就往那个方向递归查询,这个就是以根节点开始，往子节点递归查询



**其他特性**

1. level关键字，代表树形结构中的层级编号；第一层是数字1，第二层数字2，依次递增。
2. CONNECT_BY_ROOT方法，能够获取第一层集结点结果集中的任意字段的值；例CONNECT_BY_ROOT（字段名）。

**1、基本语法**

```java
select * from table [start with condition1]
connect by [prior] id=parentid

--prior 在那边就往那个方向递归查询,这个就是以根节点开始，往子节点递归查询

```



执行语句1：

```java
--从根往下查找
select t.*, level, CONNECT_BY_ROOT(remark)
from tab_test t
start with t.id = 0
connect by prior t.id = t.fid;
```



执行结果为：

```java
ID  FID  REMARK 	LEVEL  CONNECT_BY_ROOT(REMARK)

0	-1	根节点			 1		根节点
1	0	第二层节点1		2		根节点
3	1	第三层节点1		3		根节点
4	1	第三层节点2		3		根节点
2	0	第二层节点2		2		根节点
```



执行语句2：

```
--从叶子节点往根查找
select t.*, level, CONNECT_BY_ROOT(remark)
from tab_test t
start with t.id = 4
connect by t.id = prior t.fid;
```

执行结果为：

```

ID  FID  REMARK 	LEVEL  CONNECT_BY_ROOT(REMARK)
4	1	第三层节点2		1	第三层节点2
1	0	第二层节点1		2	第三层节点2
0	-1	根节点			 3    第三层节点2
```

