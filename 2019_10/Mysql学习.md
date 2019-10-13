# MySQL

------

1. ## 查看mysql所支持的引擎

使用命令： SHOW ENGINES;

```
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



