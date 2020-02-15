## Java多线程实现

**Java 多线程创建(有且仅有这两种创建方式)**  
**• java.lang.Thread（一）**  
**– 线程继承Thread类，实现run方法**  
**• java.lang.Runnable接口（二）**  
**– 线程实现Runnable接口，实现run方法** 



***java的四大主要接口***  
***Clonable:用于对象克隆***  
***Comparable：用于对象比较***  
***Serializable：用于对象序列化***  
***Runnable:用于对象线程化***  



**Java多线程启动**  
**• 启动**  
**– start方法，会自动以新进程调用run方法**  
**– 直接调用run方法，将变成串行执行**  
**– 同一个线程，多次start会报错，只执行第一次start方法**  
**– 多个线程启动，其启动的先后顺序是随机的**  
**– 线程无需关闭，只要其run方法执行结束后，自动关闭**  
**– main函数(线程)可能早于新线程结束，整个程序并不终止**  
**– 整个程序终止是等所有的线程都终止(包括main函数线程)**  



**第一条规则：**  

***1：调用run方法，来启动run方法，将会是串行运行。***  

***2：调用start方法，来启动run方法，将会是并行运行（多线程运行）***  



创建多线程的两种方式

```java
package com.wck.thread;

/**
 * 继承 Thread 方式 ，可以 直接 new Thread1().start();启动
 * @author YuXiangKaoChi
 *
 */
public class Thread1 extends Thread{

	
	public void run() {
		System.out.println("Thread:这是 Thread1 run");
	}

	public static void main(String[] args) {

		new Thread1().start();
		
	}

}

```

```java
package com.wck.thread;

/**
 * 实现Runnable 方式 ，需要新创建一个 new Thread 来启动 线程
 * 如：new Thread(new Thread2()).start();
 * @author YuXiangKaoChi
 *
 */
public class Thread2 implements Runnable{

	public static void main(String[] args) {

		new Thread(new Thread2()).start();
	}

	public void run() {
		
		System.out.println("Runnable:这是 Thread2 run");
		
	}

}

```



①没有通过start方法来启动线程的情况，而是直接使用 run 方法

```java


/**
 * 没有通过start方法来启动 线程，而是直接使用 run 方法，一直输出 ThreadDemo0 is running 串行;
 * @author YuXiangKaoChi
 *
 */
public class ThreadDemo0
{
	public static void main(String args[]) throws Exception
	{
		new TestThread0().run();
		while(true)
		{
			System.out.println("main thread is running");
			Thread.sleep(10);
		}
	}
}
 class TestThread0 extends Thread 	
{
	public void run() 
	{
		while(true)
		{
			System.out.println(" ThreadDemo0 is running");
			try {
				Thread.sleep(1000); //1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 

```

②通过：start启动的情况

```java

/**
 * 通过start方法来启动 线程，并行,可以无顺序交替进行输出,并行;
 * @author YuXiangKaoChi
 *
 */
public class ThreadDemo1
{
	public static void main(String args[]) throws Exception
	{
		new TestThread1().start();
		while(true)
		{
			System.out.println("main thread is running");
			Thread.sleep(1000);
		}
	}
}

class TestThread1 extends Thread
{
	public void run() 
	{
		while(true)
		{
			System.out.println(" TestThread1 is running");
			try {
				Thread.sleep(1000); //1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 

```



**第二条规则：**  

***1：Main线程可能早于子线程结束。***   

***2：Main线程和子线程都结束了，整个程序才算终止***  



```java

public class ThreadDemo2
{
	public static void main(String args[]) throws InterruptedException
	{
		new TestThread2().start();
//		while(true)
//		{
//			System.out.println("main thread is running");
//			Thread.sleep(1000);
//		}
	}
}
 class TestThread2 extends Thread
{
	public void run() 
	{
		while(true)
		{
			System.out.println("TestThread2" + 
			"　is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 

```



**第三条规则：**  

***1：实现Runnable的对象必须包装在Thread类里面，才可以启动。***   

***2：不能直接对Runnable的对象进行start方法***  



```java
public class ThreadDemo3
{
	public static void main(String args[])
	{
		//new TestThread3().start();
		//Runnable对象必须放在一个Thread类中才能运行
		TestThread3 tt= new TestThread3();//创建TestThread类的一个实例
		Thread t= new Thread(tt);//创建一个Thread类的实例
		t.start();//使线程进入Runnable状态，这里启动的实际就是  TestThread3 tt= new TestThread3(); 这个tt
		while(true)
		{
			System.out.println(Thread.currentThread().getName()+"  main thread is running");
			try {
				Thread.sleep(1000); //1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class TestThread3 implements Runnable //extends Thread
{
	//线程的代码段，当执行start()时，线程从此出开始执行
	public void run()
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName() +
			" is running");
			try {
				Thread.sleep(1000); //1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

```



**第四条规则：**  

***1：一个线程对象不能多次start,多次start将报异常。***   

***2：多个线程对象都start后，哪一个先执行，完全由JVM/操作系统来主导，程序员无法指定***  

```java

public class ThreadDemo4
{
	public static void main(String [] args)
	{
		//如果需要执行两次，则需要new 出两个对象，分别start 如：t,t1
		TestThread4 t=new TestThread4();
		t.start();
		//t.start();
		//t.start();
		//t.start();
		TestThread4 t1=new TestThread4();
		t1.start();		
	}
}

class TestThread4 extends Thread  
{
	public void run()
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName() +
			" is running");
			try {
				Thread.sleep(1000); //1000毫秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

```



**Java 多线程实现对比**  
**• Thread vs Runnable**  
**– *Thread占据了父类的名额，不如Runnable方便***  
**– Thread 类实现Runnable**  
**– Runnable启动时需要Thread类的支持**  
**– Runnable 更容易实现多线程中资源共享**  
**• 结论：建议实现Runnable接口来完成多线程**  



**总结**  
**• 总结**  
**– 了解Java多线程两种实现方式**  
**– 了解Java多线程运行基本规则**  