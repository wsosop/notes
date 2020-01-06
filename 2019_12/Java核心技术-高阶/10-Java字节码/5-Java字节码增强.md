## Java 字节码增强

**字节码增强(1)**  
**• 字节码操作：通常在字节码使用之前完成**  
**–源码、编译、(字节码操作)、运行**  
**• 字节码增强：运行时对字节码进行修改/调换**  
**–Java ClassLoader类加载器**  
**–<u>Java Instrument</u>**  



**字节码增强(2)**  
**• Java Instrument**  
**–JDK 5 引入，java.lang.instrument包**  
**–对程序的替换，都是通过代理程序(javaagent)进行**  
**–premain：支持在main函数之前，对类的字节码进行修改/替换**  
**–agentmain：支持在程序运行过程中，对字节码进行替换**  



**字节码增强(3)**  
**• Java 运行前代理**  
**–<u>在main函数运行之前，修改/替换某类的字节码</u>**  
**–启动Java程序时，给java.exe增加一个参数<u>javaagent:someone.jar</u>**  
**–在<u>someone.jar</u>的清单文件(manifest)指定了<u>Premain-Class:SomeAgent</u>**  
**–<u>SomeAgent</u>类中，有一个<u>premain</u>方法，此方法先于main运行**  
**–<u>premain</u>方法有一个<u>Instrumentation</u>的形参，可以调用<u>addTransformer</u>方法，增加一个<u>ClassTransformer</u>转换类**  
**–自定义一个<u>ClassTransformer</u>类 ，重写<u>tranform</u>方法，修改/替换字节码**  



**字节码增强(4)**  
**• Java 运行时代理**  
**–<u>在main函数运行时，修改某类的字节码</u>**  
**• Test调用Greeting类工作**  
**–编写AttachToTest类，对Test进程附加一个<u>agent(jar)</u>**  
**–在jar中，利用<u>Instrument</u>对Greeting类进行<u>retransformClasses</u>，重新加载**  
**–对进程附加agent，是JVMTI的技术**  
**• JVM Tool Interface,**  
**https://docs.oracle.com/javase/8/docs/technotes/guides/jvmti/**  



**字节码增强(5)**  
**• 类替换的注意事项**  
***–可以修改方法体和常量池***  
**<u>–不可以增加、修改成员变量/方法定义</u>**  
**<u>–不可以修改继承关系</u>**  
**<u>–未来版本还会增加限制条件</u>**  
**– https://docs.oracle.com/en/java/javase/11/docs/api/java.instrument/java/lang/instrument/Instrumentation.html**  



本目录下有源代码：Java字节码增强.zip



**总结**  
**• Java Agent动态修改字节码文件**  
**–premain在运行前修改字节码**  
**–agentmain在运行时修改字节码**  
**–通常和ASM搭配使用**  









