## XML简介

**为什么需要XML  
• 20  
• age, 20  
• name, Tom, age, 20  
• Student, name, Tom, age, 20  
• 数据是主体  
• 但是，单独的数据，它的含义很模糊  
• 数据+含义，适用于传输数据，而不是显示数据(HTML)**  

如：

```java
<Student>
    <name>Tom</name>
    <age>20</age>
</Student>
```



**XML基本概念**  
**• XML(eXtensible Markup Language)，www.w3.org**  
**• 可扩展标记语言：意义+数据**  
**• 标签可自行定义，具有自我描述性**  
**• 纯文本表示，跨系统/平台/语言**  
**• W3C标准(1998年，W3C发布了XML1.0，包括几乎所有的Unicode字符)**   



**XML结构**  
**• 常规语法**  
**– 任何的起始标签都必须有一个结束标签。**  
**– 简化写法，例如，<name></name>可以写为<name/>。**  
**– 大小写敏感，如<name>和<Name>不一样。**  
**– 每个文件都要有一个根元素。**  
**– 标签必须按合适的顺序进行嵌套，不可错位。**  
**– 所有的特性都必须有值，且在值的周围加上引号。**  
**– 需要转义字符，如“<”需要用\&lt;代替。**  
**– 注释：<!-- 注释内容 -->**  



**XML扩展(1)**  
**• DTD(Document Type Definition)**   
**– 定义 XML 文档的结构**  
**– 使用一系列合法的元素来定义文档结构**  
**– 可嵌套在xml文档中，或者在xml中引用**  



**XML扩展(2)**  
**• XML Schema(XSD，XML Schema Definition)**  
**– 定义 XML 文档的结构, DTD的继任者**  
**– 支持数据类型，可扩展，功能更完善、强大**  
**– 采用xml编写**  



**XML扩展(3)**  
**• XSL**  
**– 扩展样式表语言(eXtensible Stylesheet Language)**  
**– XSL作用于XML，等同于CSS作用于HTML**  
**– 内容**  
**• XSLT: 转换 XML 文档**  
**• XPath: 在 XML 文档中导航**  
**• XSL-FO: 格式化XML文档**  
**– 可进一步访问http://www.w3school.com.cn/x.asp 进行学习**  



**总结**  
**• 总结**  
**– 了解XML的基础概念**  
**– 了解XML的多个分支技术**  

