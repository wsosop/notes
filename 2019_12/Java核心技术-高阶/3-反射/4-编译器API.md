## 编译器API

**编译器API(1)**  
**• 反射**  
**–可以查看对象的类型标识**  
**–可以动态创建对象、访问其属性，调用其方法**  
**–<u>*前提：类(class文件)必须先存在*</u>**  
**• 编译器API**  
**–对.java文件即时编译**  
**–对字符串即时编译**  
**–监听在编译过程中产生的警告和错误**  
**–在代码中运行编译器(并非：Runtime命令行调用javac命令)**  



**编译器API(2)**  
**• JavaCompiler**  
**–自Java 1.6 推出，位于javax.tools包中。**  
**–可用在程序文件中的Java编译器接口(代替javac.exe)。**  
**–在程序中编译java文件，产生class文件。**  
**–run方法(继承自java.tools.Tools)：较简单。可以编译java源文件，生成class文件，但不能指定输出路径,监控错误信息, 调用后就在源码所在目录生成class文件。**  
**–getTask方法：更强大的功能。可以编译java源文件，包括在内存中的java文件(字符串)，生成class文件。**  



**编译器API(3)**  
**• Java编译器API作用**  
**–Java EE的JSP编译**  
**–在线编程环境**  
**–在线程序评判系统(Online Judge系统)**  
**–自动化的构建和测试工具**  
**–……**  



```java
└─src
        JavaCompilerTask.java
        JavaSourceFromString.java
        SimpleJavaCompiler.java
```

```java

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaCompilerTask {

	public static void main(String[] args) throws UnsupportedEncodingException {

		compileJavaFromString();
	}

	
	public static void compileJavaFromString() {
		
		StringBuilder sb = new StringBuilder();
		String className = "Hello";
		
		//sb.append("package com.test;\n");
		sb.append("public class Hello{\n");
		sb.append("public static void main(String[]args){\n");
		sb.append("System.out.print(\"hello world\"); \n");
		sb.append("}\n");
		sb.append("}");

		//将上述源码编译
		Class<?> c = compile(className, sb.toString());
		try {
			// 生成对象
			Object obj = c.newInstance();
			// 调用main方法
			Method m = c.getMethod("main", String[].class);
			m.invoke(obj, new Object[] { new String[] {} });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Class<?> compile(String className, String javaCodes) {
		
		//将字符串包装为SimpleJavaFileObject对象
		JavaSourceFromString srcObject = new JavaSourceFromString(className, javaCodes);		
		System.out.println(srcObject.getCode());
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
		
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();  
		
		//设置编译的输出目录，并包装在options中
		String flag = "-d";
		String outDir = "";
		try {
			File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			outDir = classPath.getAbsolutePath() + File.separator;
			System.out.println(outDir);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}		
		Iterable<String> options = Arrays.asList(flag, outDir);
		
		//JavaCompiler.getTask方法：以future的任务形式(多线程)，来执行编译任务
		
		// 第一个参数：额外输出流，null表示默认使用system.err
		// 第二个参数：文件管理器，null表示编译器标准文件管理器
		// 第三个参数：诊断监听器，null表示使用编译器默认方法来报告诊断信息
		// 第四个参数：编译器参数，null表示无参数
		// 第五个参数：需要经过annotation处理的类名，null表示没有类需要annotation处理
		// 第六个参数：待编译的类
		
		JavaCompiler.CompilationTask task = 
				compiler.getTask(null, fileManager, diagnosticCollector, options, null, fileObjects);
		
		//等待编译结束
		boolean result = task.call();
		if (result == true) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else			
		{
			//print the Diagnostic's information   
            for  (Diagnostic diagnostic : diagnosticCollector  
                    .getDiagnostics())  
            {  
                System.out.println("Error on line: "   
                        + diagnostic.getLineNumber() + "; URI: "   
                        + diagnostic.getSource().toString());  
            }  
		}
		return null;
	}
}
```

```java
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * A file object used to represent source coming from a string.
 * 这个类来自于chm帮助
  */

public class JavaSourceFromString extends SimpleJavaFileObject {
	private String code;

	public JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	public String getCode()
	{
		return code;
	}
}
```

```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class SimpleJavaCompiler {

	public static void main(String[] args) throws UnsupportedEncodingException {

		successCompile();
		failCompile();
	}

	public static void successCompile() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 第一个参数：输入流，null表示默认使用system.in
		// 第二个参数：输出流，null表示默认使用system.out
		// 第三个参数：错误流，null表示默认使用system.err
		// 第四个参数：String... 需要编译的文件名
		// 返回值：0表示成功，其他错误
		int result = compiler.run(null, null, null, "F:/temp/Hello1.java", "F:/temp/Hello2.java");
		System.out.println(0 == result ? "Success" : "Fail");
	}

	public static void failCompile() throws UnsupportedEncodingException {
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 第一个参数：输入流，null表示默认使用system.in
		// 第二个参数：输出流，null表示默认使用system.out
		// 第三个参数：错误流，null表示默认使用system.err
		// 第四个参数：String... 需要编译的文件名
		// 返回值：0表示成功，其他错误
		int result = compiler.run(null, null, err, "F:/temp/Hello3.java");
		if (0 == result) {
			System.out.println("Success");
		} else {
			System.out.println("Fail");
			System.out.println(new String(err.toByteArray(), Charset.defaultCharset().toString()));
		}
	}
}
```



**编译器API(4)**  
**• 基于JavaCompiler的集成工具**  
**–Janino，http://janino-compiler.github.io/janino**  
**–InMemoryJavaCompile，**  
**https://github.com/trung/InMemoryJavaCompiler**  
**–Java-Runtime-Compiler, https://github.com/OpenHFT/JavaRuntime-Compiler**  
**– Apache Commons JCI(Java Compiler Interface),**   
**http://commons.apache.org/proper/commons-jci/index.html，**  
**适用于JDK1.5及以前的版本**  



**总结**  
**• 掌握Java编译器API实现细节**  
**• 了解Java编译器API的用途**  
**• 了解相关的Java编译器API集成包**  

