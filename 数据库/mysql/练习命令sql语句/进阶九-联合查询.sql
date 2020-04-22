#进阶九-联合查询
/**
union 联合 合并：将多个查询语句的结果合并成一个结果

语法：
 查询语句1 
 union
 查询语句2
 union
 ...

应用场景：
要查询的结果来自多个表，且多个表没有直接的连接关系，但查询的信息一致时

特点：（重点****）
    1 要求多条查询语句的查询列数是一致的
    2 要求查询多条语句的查询的每一列的类型和顺序最好是一致
    3 union关键字默认去重，如果使用 union all 可以包含重复项

*/

#引入：
# 查询部门编号>90或邮箱包含a的员工信息
-- 原先的查法：
SELECT * FROM employees WHERE department_id > 90 OR email LIKE '%a%';

SELECT * FROM employees WHERE department_id > 90 
UNION 
SELECT * FROM employees WHERE email LIKE '%a%';