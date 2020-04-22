#进阶十二-常见的数据类型
/**

1 数值型
        整数
        小数
            浮点数
            定点数
2 字符型
        较短的文本：char 、varchar
        较长的文本：text、blob （较长的二进制数据）
3 日期型
        

*/

# 一、整型
/**

    分类  tinyint 、smallint、mediumint、int/Integer、bigint

    字节   1          2          3            4          8

特点：
1 如果不设置无符号和有符号，默认是有符号，如果要设置无符号，需要添加 UNSIGNED 关键字
2 如果插入的数值超出了整型的范围，会报【Out of range value】错误，
  并且插入临界值，临界值就是超出最大/最小的是最大/最小的
3 如果不设置长度，会有默认的宽度，长度代表了显示的最大宽度，
  如果不够会在左边用 0 填充，但是必须搭配ZEROFILL 使用

*/


# 如何设置无符号和有符号
CREATE TABLE tab_int(
    t1 INT, -- 有符号，正负数都行
    t2 INT UNSIGNED -- 无符号 只能整数
);

DESC tab_int;

-- 负数-456 插入无符号的字段会 报错【Out of range value for column 't2' at row 1】并且插入值为 变成0
INSERT INTO tab_int VALUES(-123,-456);
SELECT * FROM tab_int;

-- 设置了长度，指的是显示的长度，如果显示不够长度，则会填充0，看这个效果需要 添加关键字
-- 并且添加完关键字，都会成无符号的了
DROP TABLE tab_int;
CREATE TABLE tab_int(
    t1 INT(7) ZEROFILL , -- 有符号，正负数都行
    t2 INT(7) ZEROFILL UNSIGNED -- 无符号 只能整数
);

INSERT INTO tab_int VALUES(123,456);
INSERT INTO tab_int VALUES(-123,256);

SELECT * FROM tab_int;
/**
 添加完关键字 ZEROFILL 和限制长度 为7 执行结果
     t1       t2  
-------  ---------
0000123    0000456
0000000    0000256
*/


#二、小数
/**

分类：
1 浮点数

    float(M,D):
    double(M,D)：

2 定点数
    dec(M,D):
    decimal(M,D):

特点：
1 M:整数部位长度+小数部位长度
  D:小数部位长度
  如果超过范围则 插入临界值，经测试是整数 超过范围 都插入临界值，
  单单小数超出范围是 四舍五入
2 M和D是都可以省略的
  如果是decimal，则M默认是10,D默认是0 
  如果是float和double，则会根据插入的数值的精度来决定精度 即没什么精度可言 
3 需要使用比较精确的数值用decimal ，比如金钱货币计算

原则：所选择的类型越简单越好，能保存的数值类型越小越好
*/

DROP TABLE tab_float;
CREATE TABLE tab_float(
    f1 FLOAT (5,2),
    f2 DOUBLE (5,2),
    f3 DECIMAL (5,2)
);
DESC tab_float;
SELECT * FROM tab_float;
-- 插入的小数点 不够两位
INSERT INTO tab_float VALUES(123.4,123.4,123.4);
-- 插入的小数点超过两位 四舍五入
INSERT INTO tab_float VALUES(123.431,123.456,123.456);
-- 插入的整数超过 3 位 ，插入临界值
INSERT INTO tab_float VALUES(5123.45,5123.45,5123.45);
-- 插入的整数超过 3 位 ，小数也超过2位 ，插入临界值
INSERT INTO tab_float VALUES(5123.456,5123.456,5123.456);
/**
    f1      f2      f3  
------  ------  --------
123.40  123.40    123.40
123.43  123.46    123.46
999.99  999.99    999.99
999.99  999.99    999.99
*/

#三、字符型
/**
较短的文本
    char
    varchar
其他：
    binary和varbinary用于保存较短的二进制
    enum用于保存枚举
    set用于保存集合
    
较长的文本
    text
    blob(较大的二进制)


特点：

写法        M的意思           特点                                      空间的耗费        效率
char        char(M)           最大的字符数（可以省略，默认为1）         固定长度的字符    比较耗费         高

varchar     varchar(M)        最大的字符数（不可以省略，默认为1）       可变长度的字符    比较节省        低



*/
CREATE TABLE tab_char(
	c1 ENUM('a','b','c')


);

INSERT INTO tab_char VALUES('a');
INSERT INTO tab_char VALUES('b');
INSERT INTO tab_char VALUES('c');
INSERT INTO tab_char VALUES('m');
INSERT INTO tab_char VALUES('A');

SELECT * FROM tab_set;

CREATE TABLE tab_set(

	s1 SET('a','b','c','d')



);
INSERT INTO tab_set VALUES('a');
INSERT INTO tab_set VALUES('A,B');
INSERT INTO tab_set VALUES('a,c,d');


#四、日期型

/*

分类：
date只保存日期
time 只保存时间
year只保存年

datetime保存日期+时间
timestamp保存日期+时间


特点：

            字节	范围		时区等的影响
datetime	8		1000——9999	不受
timestamp	4	    1970-2038	受

*/


CREATE TABLE tab_date(
	t1 DATETIME,
	t2 TIMESTAMP

);



INSERT INTO tab_date VALUES(NOW(),NOW());

SELECT * FROM tab_date;


SHOW VARIABLES LIKE 'time_zone';

SET time_zone='+9:00';






