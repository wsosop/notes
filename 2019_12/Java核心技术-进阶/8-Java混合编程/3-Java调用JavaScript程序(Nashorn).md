## Java调用JavaScript程序(Nashorn)

**JavaScript**  
**• JavaScript语言，又称JS语言**  
**– 1995年由Netscape网景公司发布**  
**– 脚本语言(解释型语言)**  
**• 便于快速变更，可以修改运行时的程序行为**  
**• 支持程序用户的定制化**  
**– 可用于Web前端和后端开发(全栈)**  
**– JS基本教程，http://www.w3school.com.cn/b.asp**  



**Java调用JS**  
**• 脚本引擎，ScriptEngine**  
**– Nashorn，JDK 8自带的JS解释器(JDK6/7是Rhino解释器)**  
**• ScriptEngine engine = new  ScriptEngineManager().getEngineByName(“nashorn”)**  
**– 主要方法**  
**• eval，执行一段js脚本. eval(String str), eval(Reader reader)**  
**• put，设置一个变量**  
**• get，获取一个变量**  
**• createBindings, 创建一个Bindings**  
**• setBindings, 设置脚本变量使用的范围**  

```java
─src
    └─edu
        └─ecnu
                NashornShow.java
                NashornTest1.java
                NashornTest2.java
                NashornTest3.java
                sum2.js
                sum3.js
```



```java
package edu.ecnu;

import javax.script.*;

/**
 * 查看java支持的javaScript的引擎信息
 * @author YuXiangKaoChi
 *
 */
public class NashornShow {
	public static void main(String args[]) {
		ScriptEngineManager manager = new ScriptEngineManager();
		for (ScriptEngineFactory f : manager.getEngineFactories()) {
			printBasicInfo(f);
			System.out.println();
		}
 
		ScriptEngine nashorn = manager.getEngineByName("nashorn");
		if(nashorn != null) {
			System.out.println("Nashorn is present.");
		}
		else {
			System.out.println("Nashorn is not present.");
		}
	}
 
	public static void printBasicInfo(ScriptEngineFactory factory) {
		System.out.println("engine name=" + factory.getEngineName());
		System.out.println("engine version=" + factory.getEngineVersion());
		System.out.println("language name=" + factory.getLanguageName());
		System.out.println("extensions=" + factory.getExtensions());
		System.out.println("language version=" + factory.getLanguageVersion());
		System.out.println("names=" + factory.getNames());
		System.out.println("mime types=" + factory.getMimeTypes());
	}
}
```

```java
package edu.ecnu;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * 执行javaScript语言 计算结果
 * @author YuXiangKaoChi
 *
 */
public class NashornTest1 {

	public static void main(String[] args) throws ScriptException {
		//获取engine
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); 
		
		engine.put("a", 100);
		engine.put("b", 200);
		engine.eval("var c = a+b");
		String result = engine.get("c").toString();
		
		System.out.println(result);
	}

}

```

```java
package edu.ecnu;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * 调用javaScript文件 计算结果
 * @author YuXiangKaoChi
 *
 */
public class NashornTest2 {

	public static void main(String[] args) throws ScriptException {
		//获取nashorn
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); 
		SimpleBindings simpleBindings = new SimpleBindings(); // 传递参数到js
		simpleBindings.put("a", 100); 
		simpleBindings.put("b", 200); 
		
		Object result = engine.eval("load('src/edu/ecnu/sum2.js')", simpleBindings);
		System.out.println(result);
	}

}

```

```java
package edu.ecnu;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * 调用javaScript方法 计算结果
 * @author YuXiangKaoChi
 *
 */
public class NashornTest3 {

	public static void main(String[] args) throws Exception {
		//获取nashorn
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); 
		FileReader scriptFile = new FileReader("src/edu/ecnu/sum3.js");
		engine.eval(scriptFile);
        
        Invocable in = (Invocable) engine;
        String result = in.invokeFunction("sum",100,200).toString();
		System.out.println(result);
	}

}

```

sum2.js

```javascript
c = a + b;
```

sum3.js

```javascript
function sum(a, b)
{
   return a + b;
}
```



JDK支持的脚本引擎工厂

| 引擎        | 名字        | MIME类型        | 文件扩展     |
| ----------- | ----------- | --------------- | ------------ |
| Nashorn     | nashorn, js | text/javascript | js           |
| Groovy      | groovy      | 无              | groovy       |
| Renjin      | Renjin      | text/x-R        | R, r, S, s   |
| SISC Scheme | sisc        | 无              | scheme, sisc |

**总结**  
**• 通过Nashorn的脚本引擎(ScriptEngine)解析JS**  
**• 注意调用输入输出参数和路径**  



