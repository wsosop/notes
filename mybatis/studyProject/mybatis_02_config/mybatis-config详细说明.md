## mybatis-config详细说明

### ☆涵盖了基本的配置，还有以下这三个没有涵盖

### 1.typeHandlers（类型处理器）

### 2.objectFactory（对象工厂）

### 3.plugins（插件）



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

	<!-- 一、
		1.properties：mybatis可以使用properties来引入外部properties配置文件的内容，其用的最多的也就是dataSource数据库配置文件配置
		2.resource：引用类路径下的资源
		3.url：引入网络资源的路径,或者磁盘路径下的资源路径
	  -->
	<properties resource="dbconfig.properties"></properties>
	
	<!-- 二、
		settings:这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。
		setting :
			name:设置项的名称
			value:设置项的取值
	 -->
	<settings>
		<!-- 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。默认为false -->
  		<setting name="mapUnderscoreToCamelCase" value="true"/>
	</settings>
	
	
	<!-- 三、 
		类型别名:类型别名是为 Java 类型设置一个短的名字
		typeAlias:为某一个类起别名，默认的别名为类名的首字母小写，employee
		type：类的全路径
		alias:指定的类名
		
	 -->
	<typeAliases>
		<!-- 单个起别名 -->
		<!-- <typeAlias type="com.wck.mybatis.bean.Employee" /> -->
		<!-- package:为某个包下的所有类批量起别名，批量别名不区分大小写
			 name:指定包名，为当前的包和起子类后代包的每一个类都起一个默认别名（默认的别名就是类名的小写）
		 	  如果name下的子包中也包含了和name中相同的bean类名，则使用 @Alias("emp") ，@Alias这个注解
		 -->
		<package name="com.wck.mybatis.bean"/>
	</typeAliases>
	
	<!-- 四： 
		environments:多环境配置，mybatis可配置多种环境
			environment:
			每一个environment标签可以配置一个具体的环境信息,
			其中必须有的标签是transactionManager和dataSource这两个标签
			default：这个属性就是来切换某种环境，可以达到快速切换数据库环境
			id：代表当前环境的唯一标识
				transactionManager：事务管理器 这些个配置的别名在 Configuration.class源码中可以找的到
					type：事务管理器的类型JDBC|MANAGED  JDBC:JdbcTransactionFactory  MANAGED:ManagedTransactionFactory
						  自定义的事务管理器是要继承TransactionFactory
				dataSource：
					type：UNPOOLED(UnpooledDataSourceFactory)|POOLED(PooledDataSourceFactory)|JNDI(JndiDataSourceFactory)
					自定义连接池：要实现DataSourceFactory这个接口，type就是自定义数据源的全类名 
	 -->
  <environments default="development">
  	<environment id="test">
  		  <transactionManager type="JDBC"/>
	      <dataSource type="POOLED">
	        <property name="driver" value="${jdbc.driver}"/>
	        <property name="url" value="${jdbc.url}"/>
	        <property name="username" value="${jdbc.username}"/>
	        <property name="password" value="${jdbc.password}"/>
	      </dataSource>
  	</environment>
  
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
      </dataSource>
    </environment>
  </environments>
  
  
  <!-- 五：
  	多数据库厂商标识（databaseIdProvider）
  	type="DB_VENDOR" :VendorDatabaseIdProvider这个类，
  		作用就是得到数据库厂商的标识(驱动)，mysql就能根据数据库不同的厂商标识，来执行不同的sql
  	具体使用这个，在mapper文件中 使用 databaseId="mysql",指定下就可以使用了
  	-->
  <databaseIdProvider type="DB_VENDOR">
  	  <!-- property 为不同的厂商标识起别名 -->
	  <property name="SQL Server" value="sqlserver"/>
	  <property name="DB2" value="db2"/>
	  <property name="Oracle" value="oracle" />
	  <property name="MYSQL" value="mysql" />
  </databaseIdProvider>
  
  
  <!-- 六、
  		将我们写好的sql映射文件（EmployeeMapper.xml）一定要注册到全局配置文件（mybatis-config.xml）中 -->
  <!--  mappers:将sql映射注册到全局配置中 -->
  <mappers>
  	<!--
  		mapper:注册一个sql映射
  			resource：引用类路径下的sql映射文件
  			url:引用网络路径或者磁盘下的路径文件
  			class：引用（注册）接口，:使用映射器接口实现类的完全限定类名,也就是引用接口
					1、有sql映射文件，映射文件名必须和接口同名，并且放在与接口同一目录下；
					2、没有sql映射文件，所有的sql都是利用注解写在接口上;
					推荐：
						比较重要的，复杂的Dao接口我们来写sql映射文件
						不重要，简单的Dao接口为了开发快速可以使用注解；
  	 -->
   <!--   <mapper resource="mybatis/mapper/EmployeeMapper.xml"/>  -->
  <!--   <mapper class="com/wck/mybatis/dao/EmployeeMapper.java"/> -->
  
  <!-- 批量注册：映射文件名必须和接口同名，并且放在与接口同一目录下 -->
     <package name="com.wck.mybatis.dao"/> 
  
  </mappers>
</configuration>
```

