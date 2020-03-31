package com.wck.config;

/**
 * @author 御香烤翅
 * @create 2020-03-10 20:55
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wck.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * 本类有 @PropertySource  @Profile  @PropertySources  Environment（根据环境启动）  -Dspring.profiles.active=test
 */

/**
 * profile：
 *      spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 *
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C)；
 *
 * @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 * 1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3）、没有标注环境标识的bean在，任何环境下都是加载的；
 *
 */

@Configuration
@PropertySource(value = {"classpath:dbConfig.properties"})
//@Profile("test")
public class MainOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver stringValueResolver;

    //测试环境
    @Profile("test")
    @Bean(name = "testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/company");
        dataSource.setDriverClass(stringValueResolver.resolveStringValue("db.driver"));
        return dataSource;
    }


//    @Profile("test")
    @Bean
    public Yellow yellow(){
        return new Yellow();
    }



    //开发环境
    @Profile("dev")
    @Bean(name = "devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/163course");
        dataSource.setDriverClass(stringValueResolver.resolveStringValue("db.driver"));
        return dataSource;
    }


    //生产环境
    @Profile("prod")
    @Bean(name = "prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis");
        dataSource.setDriverClass(stringValueResolver.resolveStringValue("db.driver"));
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver=resolver;
    }
}
