

## Java TCP 编程

**网络通讯协议**  
**• 通讯协议：TCP和UDP**  
**• TCP：Transmission Control Protocol**   
**– 传输控制协议，面向连接的协议 ** 
**– 两台机器的可靠无差错的数据传输**  
**– 双向字节流传递 ** 
**• UDP: User Datagram Protocol**   
**– 用户数据报协议，面向无连接协议**  
**– 不保证可靠的数据传输 ** 
**– 速度快，也可以在较差网络下使用**  



**TCP(1)**  
**• TCP协议：有链接、保证可靠的无误差通讯**  
**– ①服务器：创建一个ServerSocket，等待连接**  
**– ②客户机：创建一个Socket，连接到服务器**  
**– ③服务器：ServerSocket接收到连接，创建一个Socket和客户的Socket建立专线连接，后续服务器和客户机的对话(这一对Socket)会在一个单独的线程（服务器端）上运行**  
**– ④服务器的ServerSocket继续等待连接，返回①**  

<img src=".\ServerSockt-Client.png" alt="ServerSockt-Client" style="zoom:67%;" />

