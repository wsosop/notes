## Java 字节码概述

**Java开发过程**  
**• Java开发过程**  
**–编写阶段：采用各种编辑工具，编写.java文件**  
**–编译阶段：采用javac.exe对.java文件编译，产生.class文件**  
**–运行阶段：采用java.exe加载.class文件运行**  

![java开发过程](./java开发过程.png)



**HelloWorld.class文件**

![简单的class文件](./简单的class文件.png)



**Class文件**  
**• .class文件：字节码(bytecode)文件**  
**–class文件是Java“一次编译，到处运行”的基础**  
**–class文件具备平台无关性，由JVM执行**  
**–每个class文件包含了一个类或接口或模块的定义**  
**–class文件是一个二进制文件，由JVM定义class文件的规范**  
**–任何满足这种规范的class文件都会被JVM加载运行**  
**–class文件可以由其他语言编译生成，甚至不用程序语言直接生成**  
**–JDK版本不同，所编译出.class文件略有不同**  



**class文件内容**

![class文件内容](./class文件内容.png)



**学习Class文件**  
**• .class文件的学习**  
**–*<u>字节码文件结构 </u>***  
**• *<u>了解class文件的内部各个模块的组成</u>***   
**–字节码生成**  
**• javac，编译器API, 其他编译器API(如Eclipse JDT)**  
**–*<u>字节码操作 </u>***  
**• *<u>利用工具对字节码进行查看和编辑</u>***  
**–*<u>字节码增强</u>***  
**• *<u>使用Instrument对字节码进行转换</u>***  
**–*<u>字节码反编译和混淆</u>***  



**总结**  
**• class文件是JVM生态体系的基础构成之一**  
**• 了解字节码的学习重点**  
