#进阶8-分页查询
/**
语法：
    select 查询列表 from 表1 
    【join type join 表2 
    on 连接条件
    where 筛选条件
    group by 分组条件
    having 筛选条件
    order by 排序条件】
    limit 【offset,】size
    
    offset:要显示条目的起始索引（起始索引从0开始）
    size：要显示的条目个数
    
特点：
    1.limit语句都是在查询语句的最后面
    2.公式
        要显示的当前页数为 page（从 1 开始） ,显示的条目为 size
      select 查询列表 from 表
      limit (page-1)*size,size;
      
      size 10 
      page 
      1     10
      2     20
      3     30  

*/

# 1 查询前5条员工信息
SELECT * FROM employees LIMIT 0 ,5; -- 写法和下面的一样
SELECT * FROM employees LIMIT 5;-- 写法和上面的一样，当起始为 0的时候，可以省略

#2 查询第11条-第25条
SELECT * FROM employees LIMIT 10,15;

#3 有奖金的员工信息，并且工资较高的前十名显示出来
SELECT last_name,salary FROM employees 
WHERE commission_pct IS NOT NULL 
ORDER BY salary DESC 
LIMIT 10;