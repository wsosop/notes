package com.wck.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wck.mybatis.bean.Employee;
import com.wck.mybatis.dao.EmployeeMapper;

/**
 * myBatis的测试类
 * @author YuXiangKaoChi
 * 
 * SqlSession和connection一样都是现成非安全的
 *   不能写成 private SqlSession sqlSession=null;这种形式，每次使用都应该回去新的对象
 *   
 * 接口式编程：
 * mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 
 * 两个重要的配置文件：
 * 1.mybatis-config.xml 这个文件里面包含mybatis的全局配置信息，数据库连接池信息，事务管理器信息，系统的运行环境等
 * 2.sql映射文件：保存了每个sql语句的映射信息
 */
public class MyBatisTest {
	
	private SqlSessionFactory sqlSessionFactory=null;
	
	@Before
	public void getSqlSessionFactory() {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
	 * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 
	 * 3、将sql映射文件注册在全局配置文件中
	 * 4、写代码：
	 * 		1）、根据全局配置文件得到SqlSessionFactory；
	 * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
	 * 
	 * @throws IOException
	 */
	
	@Test
	public void test() throws IOException {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
			Employee employee=sqlSession.selectOne("com.wck.mybatis.EmployeeMapper.selectEmp", 1);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 接口是编程
	 * 首先接口要和mapper文件的namespace对应的上
	 * 然后会为接口实现一个代理对象
	 * 然后可以是用接口来调mapper中的sql执行
	 */
	@Test
	public void test1() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
		System.out.println(employeeMapper.getClass());
		Employee employee=employeeMapper.getEmployeeById(1);
		System.out.println(employee);
		
		
	}

}
