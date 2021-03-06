package com.wck.mybatis.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.wck.mybatis.bean.Employee;
import com.wck.mybatis.bean.EmployeeExample;
import com.wck.mybatis.bean.EmployeeExample.Criteria;
import com.wck.mybatis.dao.EmployeeMapper;

//import com.wck.mybatis.bean.Employee;
//import com.wck.mybatis.dao.EmployeeMapper;

public class MybatisTest {

	@Test
	public void mbg() throws Exception {

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("mbg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

	private SqlSessionFactory sqlSessionFactory = null;

	// 加载数据库配置
	@Before
	public void getSqlSessionFactory() {
		System.out.println("before...");
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//MyBatis3Simple 版本测试
	@Test
	public void MyBatis3Simple() {
		System.out.println("MyBatis3Simple...");
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.selectByExample(null);
			for (Employee emp : list) {
				System.out.println(emp.getId());
			}
		} finally {
			openSession.close();
		}

	}
	
	
	//sql语句打印：DEBUG 03-04 00:45:03,985 ==>  Preparing: select id, last_name, gender, email, d_id from tbl_employee WHERE ( last_name like ? and gender = ? )   (BaseJdbcLogger.java:145) 
	//带有 or 的sql语句打印
	
	@Test
	public void MyBatis3() {
		System.out.println("MyBatis3...");

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			//查询lastName 带有 w ，并且 gender 为1 ， or email带有 w
			EmployeeExample example=new EmployeeExample();
			Criteria criteria = example.createCriteria();
			criteria.andLastNameLike("%e%");
			criteria.andGenderEqualTo("1");
			
			Criteria criteria2 = example.createCriteria();
			criteria2.andEmailLike("%w%");
			example.or(criteria2);
			
			List<Employee> list = mapper.selectByExample(example);
			for (Employee emp : list) {
				System.out.println(emp.getLastName()+" -- ,"+emp.getGender()+" -- ,"+emp.getEmail());
			}
			
		} finally {
			openSession.close();
		}

	}

}
