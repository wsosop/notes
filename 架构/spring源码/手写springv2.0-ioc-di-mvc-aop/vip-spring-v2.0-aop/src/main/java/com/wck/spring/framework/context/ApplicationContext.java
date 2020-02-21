package com.wck.spring.framework.context;

import com.wck.spring.demo.action.MyAction;
import com.wck.spring.framework.annotation.Autowire;
import com.wck.spring.framework.annotation.Controller;
import com.wck.spring.framework.annotation.Service;
import com.wck.spring.framework.aop.AopConfig;
import com.wck.spring.framework.bean.BeanDefinition;
import com.wck.spring.framework.bean.BeanPostProcessor;
import com.wck.spring.framework.bean.BeanWrapper;
import com.wck.spring.framework.context.support.BeanDefinitionReader;
import com.wck.spring.framework.core.BeanFactory;
import com.wck.spring.framework.core.FactoryBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 御香烤翅
 * @create 2020-02-16 19:25
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    //用于本地配置文件的数组
    private String [] configLocations;

    //用于读取配置文件并包装成BeanDefinitionReader
    private BeanDefinitionReader reader;

    //用于缓存注册式单例的容器
    private Map<String,Object> beanCacheMap=new ConcurrentHashMap<>();

    //用来储存所有的被代理过的对象
    private Map<String,BeanWrapper> beanWrapperMap=new ConcurrentHashMap<>();

    public ApplicationContext(String... configLocations){
        this.configLocations=configLocations;

        //执行refresh()
        refresh();
    }

    //ioc容器的主要方法
    public void refresh() {
        //1.定位
        this.reader=new BeanDefinitionReader(configLocations);

        //2.加载
        List<String> beanDefinitions=reader.loadBeanDefinitions();

        //3.注册
        doRegisty(beanDefinitions);

        //4.依赖注入
        //设置的 lazy-init=false ，要执行依赖注入
        //在这里自动调用 getBean 方法
        doAutowrited();

//        MyAction myAction=(MyAction)this.getBean("myAction");
//        myAction.query(null,null,"wck");


    }

    //开始执行自动化的依赖注入
    private void doAutowrited() {

        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry:beanDefinitionMap.entrySet()){
            String beanName=beanDefinitionEntry.getKey();
            //这里判断是否是延迟加载，值为 false 时，则进行自动 的 getBean
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }

        //进行依赖注入
        for(Map.Entry<String,BeanWrapper> beanWrapperEntry:this.beanWrapperMap.entrySet()){

            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getOriginalInstance());

        }


    }


    //填充Bean,依赖注入
    public void populateBean(String beanName ,Object instance){

        Class clazz=instance.getClass();

        //如果不是Controller和Service注解则返回
        //不是所有牛奶都叫特仑苏
        if(!(clazz.isAnnotationPresent(Controller.class)||
                clazz.isAnnotationPresent(Service.class)) ){
            return;
        }

        Field[] fields=clazz.getDeclaredFields();

        for(Field field:fields){

            if(!field.isAnnotationPresent(Autowire.class)){
                continue;
            }

            Autowire autowire=field.getAnnotation(Autowire.class);

            //获取注解的名字，可能为空，则就使用默认第一个字母小写
            String autowireBeanName=autowire.value().trim();


            System.out.println("***************field.getName()："+field.getName());//field.getName()：demoService
            System.out.println("***************field.getType().getName()："+field.getType().getName());//field.getType().getName()：com.wck.spring.demo.mvc.service.IDemoService
            if("".equals(autowireBeanName)){
                //这个是包路径名
                autowireBeanName=field.getType().getName();
            }

            field.setAccessible(true);

            try {

                System.out.println("======================= instance：" +instance +",autowireBeanName：" +
                        autowireBeanName + ",this.beanWrapperMap.get(autowireBeanName)" +
                        this.beanWrapperMap.get(autowireBeanName));

                //System.out.println("打印类对象自动调用toString:"+this.beanWrapperMap.get(autowireBeanName).getWrapperInstance());

                //自动注入的时候，注入的是代理的对象
                field.set(instance,this.beanWrapperMap.get(autowireBeanName).getWrapperInstance());

            }catch (Exception e){
                e.printStackTrace();
            }



        }



    }

    //注册，真正的把beanDefinitions注册到beanDefinitionMap中去，实现ioc
    private void doRegisty(List<String> beanDefinitions) {
        try {
            for(String className:beanDefinitions){
                //这个className是包含包名的如：className:com.wck.spring.demo.action.MyAction
                System.out.println("className:"+className);
                Class<?> beanClass=Class.forName(className);

                //其中包含了三种情况
                //1.默认是首字母小写
                //2.自定义名字
                //3.接口注入

                //如果为接口，则不能实例化，直接返回
                if(beanClass.isInterface()){
                    continue;
                }

                BeanDefinition beanDefinition=this.reader.registerBean(className);
                if(beanDefinition !=null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }

                //这里的这段代码自己得理解是，如果包下所有扫描的类中
                //有接口的实现类，实现了接口
                //则接口，也要指向实现类的对象
                //而实现类的对象也是指向实现类的对象
                Class<?>[] interfaces=beanClass.getInterfaces();
                for(Class<?> i:interfaces){
                    System.out.println("interfaces:"+i.getName());
                    //如果是多个实现类，只能覆盖
                    //为什么？因为spring没有那么智能，就是这么傻
                    //这个时候可以自定义名字
                    //如 interfaces:com.wck.spring.demo.service.IModifyService 指向 className:com.wck.spring.demo.service.impl.ModifyService
                    //className:com.wck.spring.demo.service.impl.ModifyService 指向 className:com.wck.spring.demo.service.impl.ModifyService
                    this.beanDefinitionMap.put(i.getName(),beanDefinition);

                }

                //到这里 ioc容器初始化完毕
                /**
                 * 打印出来的beanDefinitionMap：
                 * {
                 * queryService=com.wck.spring.framework.bean.BeanDefinition@68f0f72c, ----
                 * myAction=com.wck.spring.framework.bean.BeanDefinition@2b6fcb9f,
                 * pageAction=com.wck.spring.framework.bean.BeanDefinition@75de6341,
                 * com.wck.spring.demo.service.IModifyService=com.wck.spring.framework.bean.BeanDefinition@74170687, ++++
                 * com.wck.spring.demo.service.IQueryService=com.wck.spring.framework.bean.BeanDefinition@68f0f72c, ----
                 * modifyService=com.wck.spring.framework.bean.BeanDefinition@74170687 ++++
                 * }
                 */
                System.out.println("打印出来的beanDefinitionMap："+this.beanDefinitionMap);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //依赖注入从这里开始，通过读取 definition中的信息
    //然后通过反射机制，创建一个实例并返回
    //spring的做法不会将原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式
    //1.保留原有的OOP关系
    //2.我们需要对它进行扩展，进行增强（为了以后的AOP打基础）
    @Override
    public Object getBean(String beanName) {

        try {
            BeanDefinition beanDefinition=this.beanDefinitionMap.get(beanName);

            Object instance=instantionBean(beanDefinition);//创建对象
            if(instance == null){
                return null;
            }

            BeanPostProcessor beanPostProcessor=new BeanPostProcessor();
            //bean前置
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

            //***************************在这一步，这个时候，封装到BeanWrapper中就已经是代理的对象了****************
            //包含了所有this.beanDefinitionMap中的对象
            BeanWrapper beanWrapper=new BeanWrapper(instance);

            //这一步是查找配置文件和扫描所有beanDefinition，并使用Aop代理对应的前置before和after方法
            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));

            beanWrapper.setPostProcessor(beanPostProcessor);

            //放入被代理的Map中
            this.beanWrapperMap.put(beanName,beanWrapper);


            //bean后置
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);

            //返回WrapperInstance
            //通过这样一调用，相当于给我们自己留下了可操作的空间
            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    //通过传参 beanDefinition 使用反射来 创建 对象，并返回创建好的对象
    private Object instantionBean(BeanDefinition beanDefinition) {

        Object instance=null;
        String className=beanDefinition.getBeanClassName();
        try {
            //如果存在缓存的单例map中则直接拿出来
            if(beanCacheMap.containsKey(className)){
                instance=beanCacheMap.get(className);
            }else{
                //创建对象，并保存到缓存的 beanCacheMap 中
                Class<?> clazz=Class.forName(className);

                //反射创建对象
                instance=clazz.newInstance();
                this.beanCacheMap.put(className,instance);
            }

            return instance;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 这个方法是判断传进来的beanDefinition，是否符合增强的规则（expression）
     * 通过使用配置的规则（pointCut、aspectBefore、aspectAfter）来进行匹配
     * 最后进行增强
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    public AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws Exception{

        AopConfig aopConfig=new AopConfig();
        String expression=this.reader.getConfig().getProperty("pointCut");
        String[] before =this.reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after =this.reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className=beanDefinition.getBeanClassName();
        Class<?> clazz=Class.forName(className);

        //获取当前beanDefinition的所有public方法
        Method[] methods=clazz.getMethods();
        //匹配配置中的正则表达式
        Pattern pattern=Pattern.compile(expression);

        //获取要aop的类
        Class<?> classAspect = Class.forName(before[0]);

        //遍历当前beanDefinition的所有方法，匹配配置中的前置和后置方法
        for(Method m:methods){
            Matcher matcher=pattern.matcher(m.toString());
            //如果匹配的到,则创建设置aopConfig
            if(matcher.matches()){
                /*
                    aopConfig.put的参数：
                    m:对m方法进行增强
                    classAspect.newInstance()：需要增强的类的实例 ，如 LogAspect
                    new Method[]：需要增强的方法数组，如 before 、after 方法增强
                 */
                aopConfig.put(m,classAspect.newInstance(),new Method[]{
                        classAspect.getMethod(before[1]),
                        classAspect.getMethod(after[1])
                });
            }
        }
        return aopConfig;

    }


    public String[] getBeanDefinitionNames() {
        //toArray 不带参数是 返回了一个 Object[] 数组
        //toArray 带参数是返回了参数中的数组的类型
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return  reader.getConfig();
    }
}
