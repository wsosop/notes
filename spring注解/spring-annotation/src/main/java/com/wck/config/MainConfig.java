package com.wck.config;

/**
 * @author 御香烤翅
 * @create 2020-03-09 17:58
 */

import com.wck.bean.Person;
import com.wck.controller.BookController;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 配置类==配置文件
 *  本类有 @ComponentScans @Configuration @Bean
 */
//ComponentScan value 扫描的包
//excludeFilters 指定扫描排除规则
    //@ComponentScan(value = "com.wck",excludeFilters = {
    //        @ComponentScan.Filter(type = FilterType.ANNOTATION,
    //                classes = {Controller.class,Service.class})
    //})
//includeFilters 指定扫描的时候只需要包含的规则
    //@ComponentScan(value = "com.wck",includeFilters = {
    //        @ComponentScan.Filter(type = FilterType.ANNOTATION,
    //                classes = {Controller.class,Service.class})
    //},useDefaultFilters = false)

//@ComponentScans 用于存放@ComponentScan 数组，以增强多个扫描
    //@ComponentScans(value = {
    //        @ComponentScan(value = "com.wck",includeFilters = {
    //                @ComponentScan.Filter(type = FilterType.ANNOTATION,
    //                        classes = {Controller.class,Service.class})
    //        },useDefaultFilters = false)
    //})

//FilterType的使用：
    //FilterType.ANNOTATION:定义过滤的注解
    //FilterType.ASSIGNABLE_TYPE :按照指定的具体类来定义规则
    //FilterType。ASPECTJ：使用ASPECTJ表达式
    //FilterType。REGEX：定义匹配的正则过滤
    //FilterType。CUSTOM：自定义过滤规则

//ComponentScan，FilterType.CUSTOM :自定义过滤扫描规则使用
    //@ComponentScans(value = {
    //        @ComponentScan(value = "com.wck",includeFilters = {
    ////                @ComponentScan.Filter(type = FilterType.ANNOTATION,
    ////                        classes = {Controller.class,Service.class}),
    ////                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
    ////                        classes = {BookController.class}),
    //                @ComponentScan.Filter(type = FilterType.CUSTOM,
    //                        classes = {MyTypeFilter.class})
    //        },useDefaultFilters = false)



@Configuration //这个注解告诉spring这是一个配置类
//@ComponentScans //用于存放@ComponentScan 数组，以增强多个扫描
@ComponentScans(value = {
        @ComponentScan(value = "com.wck",includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                        classes = {Controller.class,Service.class})
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
//                        classes = {BookController.class})
//                @ComponentScan.Filter(type = FilterType.CUSTOM,
//                        classes = {MyTypeFilter.class})
        },useDefaultFilters = false)
})
//@ComponentScan("com.wck.bean")
public class MainConfig {
    //给容器中注册一个bean；类型为返回值的类型，id默认为方法的名称
//    @Bean("person3")
    @Bean
    public Person person(){
        return new Person("李四",19);
    }

}
