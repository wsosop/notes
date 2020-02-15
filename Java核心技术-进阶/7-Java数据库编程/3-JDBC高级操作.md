## JDBC高级操作

**Outline**  
**• 事务管理**  
**• PreparedStatement**  
**• ResultSetMetaData**  



**事务**  
**• 数据库事务，Database Transaction。**  
**• 作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。**  
**• 事务，必须满足所谓的ACID（原子性、一致性、隔离性和持久性）属性。**  
**• 事务是数据库运行中的逻辑工作单位，由DBMS中的事务管理子系统负责事务的处理。**  



**JDBC事务**  
**• 关闭自动提交，实现多语句同一事务：**  
**• connection.setAutoCommit(false);**  
**• connection.commit(); 提交事务**  
**• connection.rollback(); 回滚事务**  
**• 保存点机制**  
**– connection.setSavepoint()**  
**– connection.rollback(Savepoint)**  

```java
├─src
│  ├─main
│  │  ├─java
│  │  │      InsertTest.java
│  │  │      ResultSetMetaDataTest.java
│  │  │      TransactionTest.java
│  │  │      
│  │  └─resources
│  └─test
│      ├─java
│      └─resources
```

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionTest {

	public static void main(String[] args) throws Exception {
		// 构建Java和数据库之间的桥梁介质
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("注册驱动成功!");
		} catch (ClassNotFoundException e1) {
			System.out.println("注册驱动失败!");
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/163course";
		Connection conn = null;
		try {
			// 构建Java和数据库之间的桥梁：URL，用户名，密码
			conn = DriverManager.getConnection(url, "root", "123456");
			conn.setAutoCommit(false);//关闭JDBC的自动提交

			insertBook(conn, "insert into t_book values(101, 'aaaa', 10)");
			insertBook(conn, "insert into t_book values(102, 'bbbb', 10)");
			insertBook(conn, "insert into t_book values(103, 'cccc', 10)");
			Savepoint phase1 = conn.setSavepoint(); //设置一个保存点
			insertBook(conn, "insert into t_book values(104, 'cccc', 10)");
			insertBook(conn, "insert into t_book values(105, 'cccc', 10)");
			conn.rollback(phase1);  //回滚到phase1保存点，即上面2行无效
			conn.commit();

			System.out.println("操作成功");

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
	}

	public static void insertBook(Connection conn, String sql) {
		try {
			// 构建数据库执行者
			Statement stmt = conn.createStatement();
			//System.out.print("创建Statement成功！");

			// 执行SQL语句
			int result = stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
```



**PreparedStatement(1)**  
**• 拼接SQL字符串很危险**  



**PreparedStatement(2)**  
**• Java提供PreparedStatement，更为安全执行SQL**  
**• 和Statement区别是使用“?” 代替字符串拼接**  
**• 使用setXXX(int,Object)的函数来实现对于？的替换**  
**– 注：不需要考虑字符串的两侧单引号**  
**– 参数赋值，清晰明了，拒绝拼接错误**  
**• 提供addBatch批量更新功能**  
**• Select语句一样用ResultSet接收结果**  



**PreparedStatement(3)**  
**• 使用PreparedStatement的好处：**  
**– 防止注入攻击**  
**– 防止繁琐的字符串拼接和错误**  
**– 直接设置对象而不需要转换为字符串**  
**– PreparedStatement使用预编译速度相对Statement快很多**  

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class InsertTest {
	
	public static void main(String[] a)
	{
		//concatInsertBook();//连接字符串的插入
		//unsafeConcatInsertBook();//不安全有注入的插入
		//safeInsertBook();	//使用 prepareStatement 安全插入
		batchInsertBook();//批量插入
	}

	public static void concatInsertBook()
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
            int bookid = 10;
            String bookName = "Effective Java";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            String sql = "insert into t_book(bookid,bookname,price) values(" 
               + bookid + ", '" + bookName + "', " + price + ")";
            int result = stmt.executeUpdate(sql);
                        
            stmt.close();
            
            System.out.println("操作成功");
            
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
	
	public static void unsafeConcatInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/163course?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句
            int bookid = 10;
            String bookName = "Effective Java',50);delete from t_book;insert into t_book values(101, 'faked book";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            String sql = "insert into t_book(bookid,bookname,price) values(" 
               + bookid + ", '" + bookName + "', " + price + ");";
            System.out.println(sql);
            int result = stmt.executeUpdate(sql);
                        
            stmt.close();
            
            System.out.println("操作成功");
            
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


	public static void safeInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/163course?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_book(bookid,bookname,price) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            int bookid = 10;
            String bookName = "Effective Java',50);delete from t_book;insert into t_book values(101, 'faked book";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            pstmt.setInt(1, bookid);
            pstmt.setString(2, bookName);
            pstmt.setInt(3, price);
            
            int result = pstmt.executeUpdate();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
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

	public static void batchInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/163course?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_book(bookid,bookname,price) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            
            String bookName = "aaaaaaaaaaaaaaaa";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            for(int i=200;i<210;i++)
            {
            	pstmt.setInt(1, i);
                pstmt.setString(2, bookName);
                pstmt.setInt(3, price);
                pstmt.addBatch();
            }            
            
            pstmt.executeBatch();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        	try {
        		if(null != conn) {
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



**ResultSetMetaData**  
**• ResultSet可以用来承载所有的select语句返回的结果集**  
**• ResultSetMetaData来获取ResultSet返回的属性（如，每一行的名字类型等）**  
**– getColumnCount()，返回结果的列数**  
**– getColumnClassName(i)，返回第i列的数据的Java类名**  
**– getColumnTypeName(i)，返回第i列的数据库类型名称**  
**– getColumnType(i)，返回第i列的SQL类型**  
**• 使用ResultSetMetaData解析ResultSet**  

```java

import java.sql.*;

public class ResultSetMetaDataTest {
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
        
        String url="jdbc:mysql://localhost:3306/test";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句并返回结果到ResultSet
            ResultSet rs = stmt.executeQuery("select bookid, bookname, price from t_book order by bookid");
                        
            //获取结果集的元数据
            ResultSetMetaData meta = rs.getMetaData(); 
            int cols = meta.getColumnCount(); 
            for(int i=1;i<=cols;i++)
            {
            	System.out.println(meta.getColumnName(i) + "," + meta.getColumnTypeName(i));
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



**总结**  
**• 了解事务的基础概念和编程**  
**• 掌握JDBC的PreparedStatement**  
**• 掌握ResultSetMetaData的处理**  

