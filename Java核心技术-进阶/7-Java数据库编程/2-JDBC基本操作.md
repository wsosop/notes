## JDBC基本操作

**JDBC**  
**• Java和数据库是平行的两套系统**  
**• Java和数据库的连接方法**  
**– Native API (不能跨平台)**  
**– ODBC/JDBC-ODBC (效率很差，也无法跨平台)**  
**– JDBC （主流）: Java Database Connectivity **  
**• JDBC 1, JDK 1.1**  
**• JDBC 2 JDK 1.3~JDK1.4 **  
**• JDBC 3 JDK 5 **  
**• JDBC 4 JDK 6, (JDK 7, JDBC4.1; JDK8, JDBC4.2)**   



**Java SQL 操作类库**  
**• java.sql.*, javax.sql.*; 这2个包只是接口类**  
**• 根据数据库版本和JDBC版本合理选择**  
**• 一般数据库发行包都会提供jar包，同时也要注意区分32位**  
**和64位（数据库分32/64位， JDK也分32/64位）。**  
**• 连接字符串（样例）**  
**– jdbc:oracle:thin:@127.0.0.1:1521:dbname**  
**– jdbc:mysql://localhost:3306/mydb**  
**– jdbc:sqlserver://localhost:1433; DatabaseName=dbname**  



**Java连接数据库操作步骤**  
**• 构建连接（搭桥）**  
**– 注册驱动，寻找材质, class.forName(“…”);**  
**– 确定对岸目标 , 建桥 Connection**  
**• 执行操作（派个人过桥, 提着篮子，去拿数据）**  
**– Statement (执行者)**  
**– ResultSet(结果集)**  
**• 释放连接（拆桥） connection.close();**  



**Statement**  
**• Statement 执行者类**  
**– 使用executeQuery()执行select语句，返回结果放在ResultSet**  
**– 使用executeUpdate()执行insert/update/delete，返回修改的行数**  
**– 一个Statement对象一次只能执行一个命令**  
**• ResultSet 结果对象**  
**– next() 判断是否还有下一条记录**  
**– getInt/getString/getDouble/……**  
**• 可以按索引位置，可以按照列名**  



```java

import java.sql.*;

public class SelectTest {
    public static void main(String[] args){
    	
    	//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
            return;
        }
        
        String url="jdbc:mysql://localhost:3306/163course";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句并返回结果到ResultSet
            ResultSet rs = stmt.executeQuery("select bookid, bookname, price from t_book order by bookid");
                        
            //开始遍历ResultSet数据
            while(rs.next())
            {
            	System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getInt("price"));
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}
```

```java

import java.sql.*;

public class UpdateTest {
	public static void main(String[] args){
		//executeUpdate("update t_book set price = 300 where bookid = 1");
		//executeUpdate("insert into t_book(bookid, bookname, price) values(3, '编译原理', 90)");
		executeUpdate("delete from t_book where bookid = 3");
	}
    public static void executeUpdate(String sql)
    {
    	//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/163course";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句
            int result = stmt.executeUpdate(sql);
                        
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}
```

```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.test</groupId>
  <artifactId>MOOC19-02</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
  	<dependency>
		<groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.38</version>
    </dependency>
  </dependencies>
</project>
```

**注意事项**  
**• ResultSet不能多个做笛卡尔积连接**  
**• ResultSet最好不要超过百条，否则极其影响性能**  
**• <u>ResultSet也不是一口气加载所有的select结果数据</u>**  
**• Connection 很昂贵，需要及时close**  
**• Connection所用的jar包和数据库要匹配**  



**总结**  
**• 了解JDBC的基础概念**  
**• 了解JDBC的执行基本的增删改查语句**  
**• 了解结果集的使用和数据处理**  