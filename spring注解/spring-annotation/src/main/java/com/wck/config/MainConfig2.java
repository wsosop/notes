package com.wck.config;

import com.wck.bean.Color;
import com.wck.bean.ColorFactoryBean;
import com.wck.bean.Person;
import com.wck.bean.Red;
import com.wck.condition.LinuxCondition;
import com.wck.condition.MyImportBeanDefinitionRegistrar;
import com.wck.condition.MyImportSelector;
import com.wck.condition.WindowsCondition;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author 御香烤翅
 * @create 2020-03-10 9:21
 */

/**
 * 本类有 @Conditional @Import @Scope @Lazy @Bean FactoryBean  ImportSelector
 */


//@Conditional放在类上表示，满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class}) //导入的组件名称默认是 包名 如：com.wck.bean.Color ,@Import({Color.class,Red.class}) //导入的组件名称默认是 包名 如：com.wck.bean.Color
public class MainConfig2 {

//   * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype
//	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton
//	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request
//	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION session

    //singleton :单例 ioc容器启动时，就会调用方法创建对象放到ioc容器中
                //以后每次获取就直接从容器中拿。
    //prototype ：原型 : ioc启动时并不会去调用方法创建对象，
                // 而是每次getBean获取的时候，才会去调用方法创建对象。
    //request :同一次请求创建一个实例
    //session ：同一个session创建一个实例

    @Scope(value = "singleton")//调整作用域
    @Lazy //懒加载
    @Bean("person")//默认bean是单实例的
    public Person person(){
        return new Person("王五",21);
    }


    //@Conditional（{Condition}） ，按照一定的条件（Condition）进行判断，满足条件给容器中注册bean
    //当前场景，如果是Windows系统注册bill,linux系统注册 linus
    @Conditional({LinuxCondition.class})
    @Bean
    public Person linus(){
        return new Person("linus",21);
    }

    @Conditional({WindowsCondition.class})
    @Bean
    public Person bill(){
        return new Person("bill gates",21);
    }

    /**
     * 1).包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2).@Bean[导入的第三方包里面的组件]
     * 3).@Import[快速给容器中导入一个组件]
     *     1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     *     2)、ImportSelector:返回需要导入的组件的全类名数组
     *     3)、ImportBeanDefinitionRegistrar：手动注册容器到 bean中
     * 4).使用spring提供的FactoryBean(工厂bean)
     *     1）、默认获取到的是工厂bean调用getObject创建的对象
     *     2）、要获取工厂Bean本身，我们需要给id前面加一个&
     *     &colorFactoryBean
     */

    @Bean
    public FactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
