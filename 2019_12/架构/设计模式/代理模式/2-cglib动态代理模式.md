## CGLIB动态代理



CGLIB不需要有接口，但是需要传递所要代理的父类，通过继承来实现。

```
└─src
    ├─com
    │  └─wck
    │      ├─cglib
    │      │      CglibProxy.java
    │      │      CglibTest.java
    │      │      Dog.java
```

```java
package com.wck.cglib;

public class Dog {

	public void run(String name) {
		System.out.println("Dog: "+name+"run...");
	}
	
	public void eat() {
		System.out.println("Dog: eat...");
	}
	
}
```

```java
package com.wck.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		System.out.println("这里是cglib对目标方法的增强");
		//注意这里是使用方法调用，不是用反射！！！
		Object object=proxy.invokeSuper(obj, args);
		return object;
	}

}
```

```java
package com.wck.cglib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class CglibTest {

	public static void main(String[] args) {
		//在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\eclipseWorkspaceIcourse163\\PMOOC04-02\\cglib");
        //这个是jdk代理使用的
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer=new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);
        //设置回调函数
        enhancer.setCallback(new CglibProxy());
      //这里的creat方法就是正式创建代理类
        Dog dog=(Dog) enhancer.create();
        //调用方法
        dog.eat();
        System.out.println(dog.getClass());
        
        System.out.println(dog.getClass().getSuperclass().getName());
        System.out.println();
        
        Type[] types=dog.getClass().getGenericInterfaces();
        
        for(Type t:types) {
        	System.out.println(t);
        }
        
        
//        System.out.println("------------0----------------");
//        
//        Method[] methods=dog.getClass().getDeclaredMethods();
//        for(Method m:methods) {
//        	System.out.println(m.getName());
//        }
//        
//        //动态代理 
//        System.out.println("------------1----------------");
//        Field[] fields=dog.getClass().getDeclaredFields();
//        for(Field f:fields) {
//        	System.out.println(f.getName());
//        }
        
        
        
		
	}
}
```

