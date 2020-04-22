#进阶十一-DDL

/**
数据库定义语言
库和表的管理

一、库的管理
创建、修改、删除
二、表的管理
创建、修改、删除

创建：create
修改：alter
删除：drop

*/

#一、库的管理books
#1 库的创建
/**
语法：
create database 【IF NOT EXISTS 】库名;
*/

#1 创建 books库，如果不存在则创建
CREATE DATABASE IF NOT EXISTS books;

#2 库的修改

-- 重命名 当然这个比较危险，目前已被废弃
RENAME DATABASE books TO 新库名;

-- 更改库的字符集
ALTER DATABASE books CHARACTER SET utf8;

#3 库的删除
DROP DATABASE IF EXISTS  books;

#二、表的管理
#1 )表的创建
/**
语法：
    create table 表名(
        列名 列的类型【(长度) 约束】,
        列名 列的类型【(长度) 约束】,
        列名 列的类型【(长度) 约束】,
        列名 列的类型【(长度) 约束】,
        ...
        列名 列的类型【(长度) 约束】
    );

*/
#1 创建 book 表

CREATE TABLE book(
    id INT,#图书编号
    bname VARCHAR(20) ,#图书名
    price DOUBLE,#价格
    authorId INT ,#作者编号
    publishDate DATETIME #出版日期
);

DESC book;

# 创建表author
CREATE TABLE IF NOT EXISTS author (
    id INT,
    au_name VARCHAR(20),
    nation VARCHAR(10) #国籍
);
DESC author

# 2)表的修改
/**
1 修改列名
2 修改列的类型和约束
3 添加新列
4 删除列
5 修改表名

语法
    alter table 表名 add|drop|modify|change column 列名 【列类型 约束】;
    
    

*/
# 修改列名
ALTER TABLE book CHANGE COLUMN publishDate pubDate DATETIME;
#修改列的类型和约束
ALTER TABLE book MODIFY COLUMN pubdate TIMESTAMP;

DESC book;

# 添加新列
ALTER TABLE author ADD COLUMN annual DOUBLE;
DESC author;

# 删除列
ALTER TABLE author DROP COLUMN annual;

#修改表名
ALTER TABLE author RENAME TO book_author;

#3） 表的删除
DROP TABLE IF EXISTS book_author;

SHOW TABLES;

#通用的写法
DROP DATABASE IF EXISTS 旧库名;
CREATE DATABASE 新库名;

DROP TABLE IF EXISTS 旧表名;
CREATE TABLE 新表名();


#表的复制
INSERT INTO author VALUES
(1,'wck','China'),
(2,'wck2','China2')

SELECT * FROM author;


#1 仅仅复制 表结构
CREATE TABLE copy LIKE author;

#2 复制表的结构+数据
CREATE TABLE copy2 SELECT * FROM author;

#3 复制表结构+部分列和部分数据
CREATE TABLE copy3 SELECT id,au_name FROM author WHERE nation='China';

#4仅仅复制部分结构（某些字段）【仅仅复制 id 和 nation的表结构，不复制数据】
CREATE TABLE copy4 SELECT id,nation FROM author WHERE 0;




