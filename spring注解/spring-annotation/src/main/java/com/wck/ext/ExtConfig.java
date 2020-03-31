package com.wck.ext;

import com.wck.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 御香烤翅
 * @create 2020-03-11 22:38
 */

/**
 * 本类有 扩展的 ：
 *   BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor、ApplicationListener【@EventListener】
 *   Spring容器创建过程
 */


/**
 * 扩展原理
 *
 * 1.
 * BeanPostProcessor:bean后置处理器，bean创建对象初始化（此初始化值得是配置的init-method方法，应该在实例和依赖注入之后）前后进行拦截工作的
 * BeanFactoryPostProcessor:BeanFactory的后置处理器；（ext讨论的是这个，上面的BeanPostProcessor只是作对比，之前有用过）
 *      在bean factory 标准初始化之后调用，所有的bean定义(我的理解是beanDefinition)已经保存加载到beanFactory,但是bean的实例还未创建。
 *
 * 1)ioc容器创建对象
 * 2)invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessors
 *      如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *          1)直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *          2)在初始化创建其他组件前面执行
 *
 *2.
 * BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *      在所有bean定义信息（我的理解是beanDefinition）已加载完成，bean实例还未创建的时候，但是要优先于BeanFactoryPostProcessor；
 *
 * BeanDefinitionRegistryPostProcessor要优先于BeanFactoryPostProcessor，
 * 可以利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件
 *
 * 原理：
 *  1)ioc创建对象
 *  2)refresh()->invokeBeanFactoryPostProcessors
 *  3)从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件。
 *      1)依次触发所有的postProcessBeanDefinitionRegistry()方法
 *      2)再来触发【invokeBeanFactoryPostProcessors】postProcessBeanFactory()BeanFactoryPostProcessor;
 *  4)再来从容器中找到BeanFactoryPostProcessor组件，然后依次执行触发postProcessBeanFactory()方法
 *
 *
 * 3.
 * ApplicationListener：监听容器中发布的事件，完成事件驱动模型开发。
 *     public interface ApplicationListener<E extends ApplicationEvent>
 *         监听ApplicationEvent及其下面的子事件
 * @EventListener 这个注解也可以监听事件，是 ApplicationListener的注解实现方式
 *  自己的事件开发步骤：
 *  1)写一个监听器来监听某个事件（ApplicationEvent及其子类）
 *  2）把监听器加入到容器中
 *  3)只要容器中有相关的事件发布，我们就能监听到这个事件
 *      ContextRefreshedEvent：容器刷新完成（所有bean都完全创建）会发布这个事件
 *      ContextClosedEvent：关闭容器会发布这个事件
 *
 *  4）发布一个事件
 *       applicationContext.publishEvent();
 *
 * 原理：
 *      ContextRefreshedEvent、ExtTest$1[source=我发布了一个事件]、ContextClosedEvent
 *
 * 1)ContextRefreshedEvent事件
 *      1)ioc创建对象
 *      2)refresh()->finishRefresh()容器刷新完成,容器刷新完成会发布ContextRefreshedEvent事件
 * 2)自己发布事件；
 * 3)容器关闭会发布ContextClosedEvent；
 *      事件发布流程：
 *      3)publishEvent(new ContextRefreshedEvent(this));
 *              1)获取事件的多播器（派发器）：getApplicationEventMulticaster()
 *              2)multicastEvent()派发事件：
 *              3)获取到所有的pplicationListener
 *                   for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
 *   				 1)如果有Executor，可以支持使用Executor进行异步派发；
 *   					Executor executor = getTaskExecutor();
 *   				2)否则，同步的方式直接执行listener方法；invokeListener(listener, event);
 *    				   拿到listener回调onApplicationEvent方法；
 *
 *
 *  【事件多播器（派发器）】
 *  	1）、容器创建对象：refresh();
 *  	2）、initApplicationEventMulticaster();初始化ApplicationEventMulticaster；
 *  		1）、先去容器中找有没有id=“applicationEventMulticaster”的组件；
 *  		2）、如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *  			并且加入到容器中，我们就可以在其他组件要派发事件，自动注入这个applicationEventMulticaster；
 *
 *  【容器中有哪些监听器】
 *  	1）、容器创建对象：refresh();
 *  	2）、registerListeners();
 *  		从容器中拿到所有的监听器，把他们注册到applicationEventMulticaster中；
 *  		String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *  		//将listener注册到ApplicationEventMulticaster中
 *  		getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 *
 *   SmartInitializingSingleton 原理：->afterSingletonsInstantiated();
 *   		1）、ioc容器创建对象并refresh()；
 *   		2）、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean；
 *   			1）、先创建所有的单实例bean；getBean();
 *   			2）、获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的；
 *   				如果是就调用afterSingletonsInstantiated();
 *
 *
 */

@Configuration
@ComponentScan(value = {"com.wck.ext"})
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue();
    }



}
