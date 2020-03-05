package com.wck.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.wck.mybatis.bean.Department;
import com.wck.mybatis.bean.Employee;
import com.wck.mybatis.dao.DepartmentMapper;
import com.wck.mybatis.dao.EmployeeMapper;
import com.wck.mybatis.dao.EmployeeMapperDynamicSQL;
import com.wck.mybatis.dao.EmployeeMapperPlus;

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
	
	//加载数据库配置
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
	
	//测试查找
	@Test
	public void test() throws IOException {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				Employee employee=sqlSession.selectOne("com.wck.mybatis.EmployeeMapper.getEmployeeById", 1);
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
	//接口编程测试查找
	@Test
	public void test1() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
		System.out.println("代理对象："+employeeMapper.getClass());
		try {
			Employee employee=employeeMapper.getEmployeeById(1);
			System.out.println(employee);
		}finally {
			sqlSession.close();		
		}
	}

	/**
	 * 测试增删改
	 * 1、mybatis允许增删改直接定义以下类型返回值
	 * 		Integer、Long、Boolean、void
	 * 2、我们需要手动提交数据
	 * 		sqlSessionFactory.openSession();===》手动提交
	 * 		sqlSessionFactory.openSession(true);===》自动提交
	 * @throws IOException 
	 */
	@Test
	public void test2() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
		System.out.println("代理对象："+employeeMapper.getClass());
		try {
			//增加
			Employee employee =new Employee(null,"wck4", "wck4@.com", "1");
			employeeMapper.addEmployee(employee);
			sqlSession.commit();//提交
			System.out.println("输出返回的id"+employee);
			System.out.println("添加成功");
			
			//更新
//			employeeMapper.updateEmployee(new Employee(1, "tom2", "tom2.@@com", "0"));
//			sqlSession.commit();//提交
//			System.out.println("更新成功");
			
			//删除
//			employeeMapper.deleteEmployee(new Employee(4, "tom2", "tom2.@@com", "0"));
//			sqlSession.commit();//提交
//			System.out.println("删除成功");
			
		}finally { 
			sqlSession.close();		
		}
	}
	
	
	@Test
	public void test3() throws IOException {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
				Employee employee=employeeMapper.getEmployeeByIdAndLastName(1, "tom2");
				
//				Map<String, Object> map=new HashMap<String, Object>();
//				map.put("id", 1);
//				map.put("lastName", "tom2");
//				Employee employee=employeeMapper.getEmployeeByMap(map);
				System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void test4() throws IOException {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
				//Employee employee=employeeMapper.getEmployeeByIdAndLastNameOther("employee",1, "tom2");
				
//				Map<String, Object> map=new HashMap<String, Object>();
//				map.put("id", 1);
//				map.put("lastName", "tom2");
//				Employee employee=employeeMapper.getEmployeeByMap(map);
				
				List<Employee> eList=employeeMapper.getEmployeeByLastNameLike("%w%");
				for (Employee e : eList) {
					System.out.println(e);
				}
				
		} finally {
			sqlSession.close();
		}
	}
	

	@Test
	public void test5() throws IOException {
		System.out.println("test5:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapper employeeMapper=sqlSession.getMapper(EmployeeMapper.class);
				Map<Integer, Employee> maps=employeeMapper.getEmployeeByLastNameLikeMap("%w%");
			
				System.out.println(maps);
				for(Entry<Integer, Employee> m: maps.entrySet()) {
					System.out.println("key:"+m.getKey()+",value:"+m.getValue());
				}
				
		} finally {
			sqlSession.close();
		}
	}
	
	//使用resultMap进行自定义列和javabean的对应关系
	@Test
	public void test6() throws IOException {
		System.out.println("test6:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapperPlus employeeMapperPlus=sqlSession.getMapper(EmployeeMapperPlus.class);
				Employee employee=employeeMapperPlus.getEmployee(1);
			
				System.out.println(employee);
				
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	@Test
	public void getCaseEmployee() throws IOException {
		System.out.println("getCaseEmployee:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapperPlus employeeMapperPlus=sqlSession.getMapper(EmployeeMapperPlus.class);
				Employee employee=employeeMapperPlus.getCaseEmployee(1);
			
				System.out.println(employee);
				System.out.println(employee.getDept());
				
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void getCaseEmployeeByStep() throws IOException {
		System.out.println("getCaseEmployeeByStep:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				EmployeeMapperPlus employeeMapperPlus=sqlSession.getMapper(EmployeeMapperPlus.class);
				Employee employee=employeeMapperPlus.getCaseEmployeeByStep(1);
				System.out.println(employee.getLastName());
				//System.out.println(employee.getDept());
				
		} finally {
			sqlSession.close();
		}
	}
	
	//使用collection定义关联集合类型的属性的封装规则，即部门中查出所有的员工使用List接收
	@Test
	public void getDeptByIdPlus() throws IOException {
		System.out.println("getDeptByIdPlus:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
			
				Department department=departmentMapper.getDeptByIdPlus(1);
				System.out.println(department);
				System.out.println(department.getEmps());
		} finally {
			sqlSession.close();
		}
	}
	
	//通过分段查询,先查出部门的信息，再通过部门的信息来查询员工的信息
	@Test
	public void getDeptByIdStep() throws IOException {
		System.out.println("getDeptByIdStep:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
			
				Department department=departmentMapper.getDeptByIdStep(1);
				System.out.println(department);
				System.out.println(department.getEmps());
		} finally {
			sqlSession.close();
		}
	}
	
	

	@Test
	public void getEmpsbyConditionDynamicSql() throws IOException {
		System.out.println("getEmpsbyConditionDynamicSql:");
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				
//				Employee emp=new Employee();
//				emp.setId(1);
//				emp.setLastName("Admin");
				EmployeeMapperDynamicSQL employeeMapperDynamicSQL=sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

				
				//这个是 if/where的测试
				//查询的时候如果某些条件没带可能sql拼装会有问题
				//1、给where后面加上1=1，以后的条件都and xxx.
				//2、mybatis使用where标签来将所有的查询条件包括在内。mybatis就会将where标签中拼装的sql，多出来的and或者or去掉
					//where只会去掉第一个多出来的and或者or。
//		
//				List<Employee> employee=employeeMapperDynamicSQL.getEmpsByConditionIf(emp);
//				System.out.println(employee);
				
				//这个为trim 测试
//				List<Employee> employee2=employeeMapperDynamicSQL.getEmpsByConditionTrim(emp);
//				System.out.println(employee2);
				
				//这个为测试choose
//				List<Employee> employee2=employeeMapperDynamicSQL.getEmpsByConditionChoose(emp);
//				System.out.println(employee2);

				//这个是测试set
//				employeeMapperDynamicSQL.updateEmp(emp);
//				sqlSession.commit();
				
				
				//这个是测试foreach
				List<Employee> employee2=employeeMapperDynamicSQL.getEmpsByConditionForeach(Arrays.asList(1,2,3));
				System.out.println(employee2);
				
		} finally {
			sqlSession.close();
		}
	}
	
	
	@Test
	public void batchAddEmp() {
		System.out.println("batchAddEmp:");
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			
			List<Employee> list=new ArrayList<Employee>();
			
			list.add(new Employee("lis5", "lis5", "1", new Department(1)));
			list.add(new Employee("lis6", "lis6", "1", new Department(2)));
			
//			emp.setId(1);
//			emp.setLastName("Admin");
			EmployeeMapperDynamicSQL employeeMapperDynamicSQL=sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			employeeMapperDynamicSQL.addbatchEmps(list);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	//此测试，对于"_databaseId=='mysql'" 这个 _databaseId 不能测试成功
	@Test
	public void testInnerParam() throws IOException{
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try{
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee2 = new Employee();
			employee2.setLastName("%w%");
			List<Employee> list = mapper.getEmpsTestInnerParameter(employee2);
			for (Employee employee : list) {
				System.out.println(employee);
			}
		}finally{
			sqlSession.close();
		}
	}
	
	
	
}
