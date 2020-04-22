#进阶十四-标识列

/**
又称为：自增长列
含义：可以不用手动的插入值，系统会自动的提供默认的序列值

特点：
1、标识列必须和主键搭配吗？不一定，但要求是一个key
2、一个表可以有几个标识列？至多一个！
3、标识列的类型只能是数值型
4、标识列可以通过 SET auto_increment_increment=3;设置步长
可以通过 手动插入值，设置起始值

*/

#一、创建表时设置标志列

DROP TABLE IF EXISTS tab_increment;

CREATE TABLE tab_increment(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20)
);
INSERT INTO tab_increment VALUES(NULL,'wck');
SELECT * FROM tab_increment;

-- 显示变量 AUTO_INCREMENT
SHOW VARIABLES LIKE '%AUTO_INCREMENT%';
/**
Variable_name             Value   
------------------------  --------
auto_increment_increment  1           步长值，每次增加的 都为1   ，可以自定义设置
auto_increment_offset     1           起始值 ，  不允许自定义设置
*/

-- 设置步长的值
SET auto_increment_increment=1;


#二、修改表时设置标识列
ALTER TABLE  tab_increment MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT;

#三、修改表时删除标识列
ALTER TABLE  tab_increment MODIFY COLUMN id INT ;







