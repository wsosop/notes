package com.wck.test;

import com.wck.bean.Yellow;
import com.wck.config.MainConfigOfAutoWired;
import com.wck.config.MainOfProfile;
import com.wck.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class ProfileTest {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainOfProfile.class);

    /**
     * 测试 @Profile
     *
     */
    //1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
    //2、代码的方式激活某种环境；
    @Test
    public void test01(){

        System.out.println("-------------------------------");

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        //1、创建一个applicationContext
        //2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("prod");
        //3、注册主配置类
        applicationContext.register(MainOfProfile.class);
        //4、启动刷新容器
        applicationContext.refresh();


        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String string : namesForType) {
            System.out.println(string);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);
        applicationContext.close();

    }

}
