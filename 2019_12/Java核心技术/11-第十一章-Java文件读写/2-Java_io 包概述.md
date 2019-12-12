## Java Io 包概述

**Java IO 包(1)**
**• 文件系统和Java是两套系统**
**• Java读写文件，只能以(数据)流的形式进行读写**
**• java.io包中**
**–节点类：直接对文件进行读写**
**–包装类**
**• 转化类：字节/字符/数据类型的转化类**
**• 装饰类：装饰节点类**



**Java IO 包(2)**
**• 字节：byte, 8bit, 最基础的存储单位 1Byte(字节)=8bits(位) 意思是 1个字节是由 8个0/1 组成 ，即00000000至11111111**
**• 字符：a, 10000, 我，の  ；“a” 1个字符 ；"10000" 5个字符 ；"我" 一个字符； "の" 1个字符；**
**• 数据类型: 3[整数]， 5.25[小数]，abcdef[字符串]**
**• 文件是以字节保存，因此程序将变量保存到文件需要转化，意思就是说所有的数据类型也就是数据都是需要使用字节来保存**



**Java IO 包(3)**
**• 节点类: 直接操作文件类**
**–InputStream(字节)【数据从文件读到Java中去】, OutputStream(字节)【数据从Java输出到文件里】**
**• FileInputStream[相应的子类],FileOutputStream[相应的子类]**
**–Reader(字符),Writer(字符)**
**• FileReader[相应的子类], FileWriter[相应的子类]**

**<u>字节的类都是以 stream 结尾，而字符的类通常是以er来结尾的。</u>**



**Java IO 包(4)**
**• 转换类：字符到字节之间的转化**
**–InputStreamReader：文件读取时字节，转化为Java能理解的字符**
**–OutputStreamWriter：Java将字符转化为字节输入到文件中**
**• 装饰类：装饰节点类**
**–DataInputStream,DataOutputStream: 封装数据流**
**–BufferedInputStream,BufferOutputStream：缓存字节流**
**–BufferedReader, BufferedWriter：缓存字符流**



**Java IO 包(4)**
**• 总结**
**–Java文件处理类都在java.io包中**
**–处理类分为：节点类、包装类(转化类、装饰类)**

