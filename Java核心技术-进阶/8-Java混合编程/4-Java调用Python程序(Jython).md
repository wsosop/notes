

## Java调用Python程序(Jython)

**Python**  
**• Python语言**  
**– 1989年由Guido van Rossum设计发明**  
**– 解释型脚本语言**  
**– 可用于科学计算软件/Web/桌面软件开发 ** 
**– 目前版本3 ** 



**Jython(1)**   
**• Jython(曾用名JPython)**  
**– Jython是Python语言在Java平台的实现**  
**– Jython是在JVM上实现的Python，由Java编写**  
**– Jython将Python源码编译成JVM字节码，由JVM执行对应的字节码，因此能很好的与JVM集成。Jython并不是Java和Python的连接器。**  
**– http://www.jython.org , 当前版本2.7**  



**Jython(2)**  
**• 关键类**  
**– PythonInterpreter**  
**• exec 执行语句**  
**• set 设置变量值**  
**• get 获取变量值**  
**• execfile执行一个python文件**  
**– PyObject**  
**– PyFunction**  



├─src
│  ├─main
│  │  ├─java
│  │  │  └─edu
│  │  │      └─ecnu
│  │  │              calculator1.py
│  │  │              calculator2.py
│  │  │              hello.py
│  │  │              JythonTest1.java
│  │  │              JythonTest2.java
│  │  │              JythonTest3.java
│  │  │              JythonTest4.java
│  │  │              JythonTest5.java
│  │  │              randomSum.py
│  │  │              
│  │  └─resources
│  └─test
│      ├─java
│      └─resources

│  pom.xml

```java
package edu.ecnu;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest1 {

	public static void main(String[] args) {
		PythonInterpreter pi = new PythonInterpreter();
	    // 执行Python程序语句
	    pi.exec("import sys");
	    pi.set("a", new PyInteger(42));
	    pi.exec("print a");
	    pi.exec("x = 2+2");
	    PyObject x = pi.get("x");
	    System.out.println("x: " + x);

	}

}
```



```java
package edu.ecnu;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest2 {

	public static void main(String[] args) {
		PythonInterpreter pi = new PythonInterpreter();
	    pi.execfile("src/main/java/edu/ecnu/hello.py");
	    pi.cleanup();
	    pi.close();

	}
}

```

```java
package edu.ecnu;

import javax.script.ScriptException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest3 
{
	
	/**
	 * 随机输出cnt个随机数值，并计算和
	 * cnt由Java代码设置
	 * randomSum.py中使用java Random类生成随机数并计算和
	 * @param args
	 * @throws ScriptException
	 */
    public static void main( String[] args ) throws ScriptException
    {
    	try (PythonInterpreter pi = new PythonInterpreter()) {
	    	pi.set("cnt", 5);
	    	pi.execfile("src/main/java/edu/ecnu/randomSum.py");
	    	PyObject sum = pi.get("sum");
	    	System.out.println("Sum is: " + sum);
    	}
    }    
}

```

```java
package edu.ecnu;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest4 {

	public static void main(String[] args) {
		
		PythonInterpreter pi = new PythonInterpreter();	    
	    pi.execfile("src/main/java/edu/ecnu/calculator1.py");  // 加载python程序
	    
	    // 调用Python程序中的函数
	    PyFunction pf = pi.get("power", PyFunction.class);
	    PyObject result = pf.__call__(Py.newInteger(2), Py.newInteger(3)); //2^3
	    System.out.println(result);
	    pi.cleanup();
	    pi.close();    	    
	}
}

```

```java
package edu.ecnu;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonTest5 {

	public static void main(String[] args) {
		
		
	    PythonInterpreter pi = new PythonInterpreter();
	    pi.execfile("src/main/java/edu/ecnu/calculator2.py");
	    
	    
	    //在Java中调用Python对象实例的方法
	    String pythonClassName = "Calculator";  // python类名	    
	    String pythonObjName = "cal"; // python对象名    
	    pi.exec(pythonObjName + "=" + pythonClassName + "()"); // 实例化python对象	    
	    PyObject pyObj = pi.get(pythonObjName); // 获取实例化的python对象
	    
	    // 调用python对象方法,传递参数并接收返回值
	    PyObject result = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)}); 
	    double power = Py.py2double(result);
	    System.out.println(power);
	    
	    pi.cleanup();
	    pi.close();
	}
}

```

calculator1.py：

```python
# coding=utf-8
import math

# 面向函数式编程
def power(x, y):
    return math.pow(x, y) 
```

calculator2.py:

```python
# coding=utf-8
import math

# 面向对象编程
class Calculator(object):
    
    # 计算x的y次方
    def power(self, x, y):
        return math.pow(x,y)
```

hello.py:

```python
# coding=utf-8
print("Hello World")
print("你好")
```

randomSum.py:

```python
from java.util import Random
r = Random()
sum = 0
for i in xrange(cnt):
	randomNum = r.nextInt()
	print randomNum
	sum += randomNum
```



pom:

```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.test</groupId>
  <artifactId>MOOC20-04</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
	<!-- https://mvnrepository.com/artifact/org.python/jython-standalone -->
	<dependency>
	    <groupId>org.python</groupId>
	    <artifactId>jython-standalone</artifactId>
	    <version>2.7.0</version>
	</dependency>
  </dependencies>
</project>
```



**总结**  
**• Java通过Jython可以执行Python代码**  
**• Python代码都会被解释为Java字节码进行执行**  
**• Jython适合用来编写小应用程序，增加程序动态性**  