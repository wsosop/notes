## Java调用Web Service

**• Web Service 示例**  
**– http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx **  
**– http://www.webxml.com.cn/webservices/weatherwebservice.asmx**  



**Java 调用 Web Service(1)**  
**• Java提供wsimport 工具**  
**– %JAVA_HOME%\bin目录下**  
**– 根据wsdl文档，自动产生客户端中间代码**  
**– wsimport -keep -verbose http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL**  



**Java 调用 Web Service**  
**– 调用wsimport所产生客户端中间代码**  
**– 提供相应参数**  
**– 获取返回结果**  



**Java 调用 Web Service(3)**  
**• Java 调用 Web Service 其他办法**  
**– Axis/Axis2 (axis.apache.org)**  
**– 采用URLConnection访问 Web Service**  
**– 采用HttpClient访问Web Service**  

代码在本目录下 WebService.zip

**总结**  
**• Web Service是一种在线服务，可以用任何语言编写**  
**• 通过wsimport工具，可以生成Web Service的中间代码类**  
**• 调用中间代码类，即可调用Web Service(异构语言实现的)行为方法**  







