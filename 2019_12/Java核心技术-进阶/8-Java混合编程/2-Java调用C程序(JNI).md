## Java调用C程序(JNI)

**JNI(1)**  
**• Java和C互操作**  
**– JNI，Java Native Interface**  
**– Java和本地C代码进行互操作**  
**• Java调用C程序完成一些需要快速计算的功能(常见，重点)**  
**• C调用Java程序(基于反射的方法)**  



<img src=".\java调用C示意图.png" alt="java调用C示意图" style="zoom:67%;" />



**JNI(3)**  
**• 在Java类中声明一个本地方法**  
**• 调用javac.exe编译，得到HelloNative.class**  
**• 调用javah.exe得到包含该方法(Java_HelloNative_greeting)的头文件HelloNative.h**  
**• 实现.c文件(对应HelloNative.h)**  
**• 将.c和.h文件，整合为共享库(DLL)文件**  
**• 在Java类中，加载相应的共享库文件**  



代码部分，放在本目录中 JavaToC.zip。



**JNI(5)**  
**• Java数据类型和C数据类型**    

| Java类型 | C类型    | 字节 | Java类型 | C类型   | 字节 |
| -------- | -------- | ---- | -------- | ------- | ---- |
| boolean  | jboolean | 1    | int      | jint    | 4    |
| byte     | jbyte    | 1    | long     | jlong   | 8    |
| char     | jchar    | 2    | float    | jfloat  | 4    |
| short    | jshort   | 2    | double   | jdouble | 8    |



**JNI(6)**  
**• 总结**  
**– 通过JNI，可以实现Java调用C程序进行计算**  
**– 采用JNI，将丧失跨平台性**  