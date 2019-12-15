## Netty 编程

**Netty(1)**  
**• Netty, http://netty.io **  
**• 最早由韩国Trustin Lee 设计开发的**  
**• 后来由JBoss接手开发，现在是独立的Netty Project**  
**• 一个非阻塞的客户端-服务端网络通讯框架**  
**• 基于异步事件驱动模型**  
**• 简化Java的TCP和UDP编程**  
**• 支持HTTP/2， SSL等多种协议**  
**• 支持多种数据格式，如JSON等**  



**Netty(2)**
**• 关键技术**
**– 通道 Channel(用来数据交换的)**
**• ServerSocketChannel/NioServerSocketChannel/…**
**• SocketChannel/NioSocketChannel**
**– 事件 EventLoop**
**• <u>为每个通道定义一个EventLoop，处理所有的I/O事件</u>**
**• <u>EventLoop注册事件</u>**
**• <u>EventLoop将事件派发给ChannelHandler</u>**
**• <u>EventLoop安排进一步操作</u>**



**Netty(3)**
**• 关键技术**
**– 事件**
**• 事件按照 <u>数据流向</u> 进行分类**
**• 入站事件（对于netty来说，数据送到我这里来就叫入站）：连接激活/数据读取/……**
**• 出站事件（对于netty来说，我的数据要出去了就叫出站：打开到远程连接/写数据/……**
**– 事件处理 ChannelHandler**
**• <u>Channel通道发生数据或状态改变</u>**
**• <u>EventLoop会将事件分类，并调用ChannelHandler的回调函数</u>**
**• <u>程序员需要实现ChannelHandler内的回调函数</u>**
**• <u>ChannelInboundHandler（入站事件）/ChannelOutboundHandler（出站事件）</u>**



**Netty(4)**
**• 关键技术**
**– ChannelHandler工作模式：责任链**
**• 责任链模式**
**– 将请求的接收者连成一条链**
**– 在链上传递请求，直到有一个接收者处理该请求**
**– 避免请求者和接收者的耦合**
**• ChannelHandler可以有多个，依次进行调用**
**• ChannelPipeline作为容器，承载多个ChannelHandler**
**– ByteBuf** 
**• 强大的字节容器，提供丰富API进行操作**

<img src=".\责任链示意图.png" alt="责任链示意图"  />
<img src=".\netty工作模式-责任链.png" alt="netty工作模式-责任链"  />



**Netty(5)**  
**• 进一步阅读书籍**  
**– 《Netty 实战》，Norman Maurer 著，何品 译，人民邮电出版**  
**社，2017.**  
**– 《Netty权威指南》，李林锋，电子工业出版社，2015.**  

具体代码见目录下的文件压缩包Netty.zip。



**Netty(6)**  
**• Mina**  
**– Apache Mina, http://mina.apache.org/ **  
**– NIO 框架库**  
**– 事件驱动的异步网络通讯**  
**– 和Netty的区别**  
**https://stackoverflow.com/questions/1637752/netty-vs-apache-mina**  



**总结**  
**• 了解Netty的基本原理**  
**• 掌握Netty的基本编程**  

