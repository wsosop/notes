## Java调用命令行

**命令行**  
**• 很多程序没有源码，但是可以执行**  
**– 命令行的执行方式**  
**– 可以带输入参数，可以有输出结果**  



**Runtime**  
**• Java提供Runtime类**  
**– exec 以一个独立进程执行命令command, 并返回Process句柄**  
**– 当独立进程启动后，需要处理该进程的输出流/错误流**  
**• 调用Process.getInputStream 可以获取进程的输出流**  
**• 调用Process.getErrorStream可以获取进程的错误输出流**  
**– 调用Process.waitFor 等待目标进程的终止(当前进程阻塞)**  

```java
└─src
        JavaExec0.java
        JavaExec1.java
        JavaExec2.java
        JavaExec3.java
        JavaExec4.java
```

```java

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExec0 {
	public static void main(String[] args) {
		Process p;
		String cmd = "ipconfig /all";  //查看ip地址

		try {
			// 执行命令
			p = Runtime.getRuntime().exec(cmd);
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(fis);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// 直到读完为止
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("");
            int exitVal = p.waitFor(); //获取进程最后返回状态
            System.out.println("Process exitValue: " + exitVal);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

```java

import java.util.*;
import java.io.*;

public class JavaExec1 {
    public static void main(String args[]) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("javac");
            int exitVal = p.exitValue(); // 进程还没结束，容易爆发异常!
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
```

```java

import java.util.*;
import java.io.*;

public class JavaExec2 {
    public static void main(String args[]) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("javac");
            InputStream stderr = p.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("=======error==========");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("");
            int exitVal = p.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
```

```java

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExec3 {
	public static void main(String[] args) {
		Process p;
		String[] cmds = new String[2];
		cmds[0] = "javac"; 
		cmds[1] = "c:/temp/HelloWorld.java"; 
		
		try {
			// 执行命令
			p = Runtime.getRuntime().exec(cmds);
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(fis);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// 直到读完为止
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("");
            int exitVal = p.waitFor(); //获取进程最后返回状态
            System.out.println("Process exitValue: " + exitVal);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

```java

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExec4 {
	public static void main(String[] args) {
		Process p;
		String[] cmds = new String[2];
		cmds[0] = "java"; 
		cmds[1] = "HelloWorld"; 
		
		try {
			// 执行命令
			p = Runtime.getRuntime().exec(cmds, null, new File("c:/temp"));
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(fis);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			// 直到读完为止
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("");
            int exitVal = p.waitFor(); //获取进程最后返回状态
            System.out.println("Process exitValue: " + exitVal);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```



**总结**  
**• 理解Runtime执行机制**  
**• 熟悉exec等函数用法(传递参数/设置环境变量/工作目录等)**  