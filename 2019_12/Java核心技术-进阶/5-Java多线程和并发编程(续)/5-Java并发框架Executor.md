## Java并发框架Executor

**并行计算(1)**  
**• 业务：任务多，数据量大**  
**• 串行 vs 并行**  
**– 串行编程简单，并行编程困难**  
**– 单个计算核频率下降，计算核数增多，整体性能变高**  
**• 并行困难(任务分配和执行过程*高度耦合*)**  
**– 如何控制粒度，切割任务**  
**– 如何分配任务给线程，监督线程执行过程**  



**并行计算(2)**  
**• 并行模式**  
**– 主从模式 (Master-Slave)**  
**– Worker模式(Worker-Worker)**  
**• Java并发编程**  
**– Thread/Runnable/Thread组管理**  
**– Executor(本节重点)**  
**– Fork-Join框架**  



**线程组管理**  
**• 线程组ThreadGroup**  
**– 线程的集合**  
**– 树形结构，大线程组可以包括小线程组**  
**– 可以通过enumerate方法遍历组内的线程，执行操作**  
**– 能够有效管理多个线程，但是管理效率低**  
**– 任务分配和执行过程高度耦合**  
**– 重复创建线程、关闭线程操作，无法重用线程**  
**• 参看例子**  



`activeCount` ,返回线程组中还处于 active的线程数(估计数)；

`enumerate`,将线程组中active的线程拷贝到数组中;

`interrupt`,对线程组中所有的线程发出 interrupt信号;

`list`,打印线程组中所有的线程信息；



```java
└─src
    ├─executor
    │  ├─example1
    │  │      Main.java
    │  │      Server.java
    │  │      Task.java
    │  │      
    │  └─example2
    │          SumTask.java
    │          SumTest.java
    │          
    └─threadgroup
            Main.java
            Result.java
            Searcher.java
```

```java
package threadgroup;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		// 创建线程组
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result=new Result();

		// 创建一个任务，10个线程完成
		Searcher searchTask=new Searcher(result);
		for (int i=0; i<10; i++) {
			Thread thread=new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("========华丽丽0=======");
		
		// 查看线程组消息
		System.out.printf("active 线程数量: %d\n",threadGroup.activeCount());
		System.out.printf("线程组信息明细\n");
		threadGroup.list();
		System.out.println("========华丽丽1=======");

		// 遍历线程组
		Thread[] threads=new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i=0; i<threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
		}
		System.out.println("========华丽丽2=======");

		// Wait for the finalization of the Threadds
		//如果该线程组的所有活动线程大于 9 则休眠 1秒钟
		waitFinish(threadGroup);
		
		// Interrupt all the Thread objects assigned to the ThreadGroup
		//中断此线程组的所有线程
		threadGroup.interrupt();
	}

	public static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount()>9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

```



**Executor(1)  
• 从JDK 5开始提供Executor FrameWork (*java.util.concurrent.)*  
– 分离任务的创建和执行者的创建  
– 线程重复利用(*new线程代价很大*)  
• 理解*共享线程池*的概念  
– 预设好的多个Thread，可弹性增加  
– 多次执行很多很小的任务  
– 任务创建和执行过程解耦  
– *程序员无需关心线程池执行任务过程***  



**Executor(2)**  
**• 主要类：ExecutorService, ThreadPoolExecutor，Future**  
**– Executors.newCachedThreadPool/newFixedThreadPool 创建线程池**  
**– ExecutorService 线程池服务**  
**– Callable 具体的逻辑对象(线程类)**  
**– Future 返回结果**  
**• 参看例子**  



```java
package executor.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行服务器
 *
 */
public class Server {
	
	//线程池
	private ThreadPoolExecutor executor;
	
	public Server(){
		executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		//executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
	}
	
	//向线程池提交任务
	public void submitTask(Task task){
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task); //执行  无返回值
		
		System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
	}

	public void endServer() {
		executor.shutdown();
	}
}

```

下面的这个例子好好理解下：

```java
package executor.example1;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Task 任务类
 * @author Tom
 *
 */
public class Task implements Runnable {

	private String name;
	
	public Task(String name){
		this.name=name;
	}
	
	public void run() {
		try {
			Long duration=(long)(Math.random()*1000);
			System.out.printf("%s: Task %s: Doing a task during %d seconds\n",Thread.currentThread().getName(),name,duration);
			Thread.sleep(duration);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("%s: Task %s: Finished on: %s\n",Thread.currentThread().getName(),name,new Date());
	}

}

```

```java
package executor.example1;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// 创建一个执行服务器
		Server server=new Server();
		
		// 创建100个任务，并发给执行器，等待完成
		for (int i=0; i<100; i++){
			Task task=new Task("Task "+i);
			Thread.sleep(10);
			server.submitTask(task);
		}		
		server.endServer();
	}
}
```

**总结**  
**• 掌握共享线程池原理**  
**• 熟悉Executor框架，提高多线程执行效率**  

