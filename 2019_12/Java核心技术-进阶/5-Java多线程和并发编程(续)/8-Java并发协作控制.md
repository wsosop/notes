## Java并发协作控制

**线程协作**  
**• Thread/Executor/Fork-Join**  
**– 线程启动，运行，结束**  
**– 线程之间缺少协作**  
**• synchronized 同步**  
**– 限定只有一个线程才能进入关键区**  
**– 简单粗暴，性能损失有点大  ×**  



**Lock**  
**• Lock也可以实现同步的效果**  
**– 实现更复杂的临界区结构**  
**– tryLock方法可以预判锁是否空闲**  
**– 允许分离读写的操作，多个读，一个写**  
**– 性能更好**  
**• ReentrantLock类，可重入的互斥锁**  
**• ReentrantReadWriteLock类，可重入的读写锁**  
**• lock和unlock函数**  

```java
└─src
        CountDownLatchExample.java
        CyclicBarrierExample.java
        ExchangerExample.java
        LockExample.java
        PhaserExample.java
        SemaphoreExample.java
        
```

```java


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 、ReentrantReadWriteLock 比原来的synchronized 提供更细粒度的控制
  *  它可以允许说：
 * 1.可以允许几个线程进入关键区
 * 2.读和写是分离的
 *
 */
public class LockExample {

	private static final ReentrantLock queueLock = new ReentrantLock(); //可重入锁
	private static final ReentrantReadWriteLock orderLock = new ReentrantReadWriteLock(); //可重入读写锁
	
	/**
	 * 有家奶茶店，点单有时需要排队 
	 * 假设想买奶茶的人如果看到需要排队，就决定不买
	 * 又假设奶茶店有老板和多名员工，记单方式比较原始，只有一个订单本
	 * 老板负责写新订单，员工不断地查看订单本得到信息来制作奶茶，在老板写新订单时员工不能看订单本
	 * 多个员工可同时看订单本，在员工看时老板不能写新订单
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//buyMilkTea();
		handleOrder(); //需手动关闭
	}
	
	public void tryToBuyMilkTea() throws InterruptedException {
		boolean flag = true;
		while(flag)
		{
			if (queueLock.tryLock()) {
				//queueLock.lock();
				long thinkingTime = (long) (Math.random() * 500);
				Thread.sleep(thinkingTime);
				System.out.println(Thread.currentThread().getName() + "： 来一杯珍珠奶茶，不要珍珠");
				flag = false;
				queueLock.unlock();
			} else {
				//System.out.println(Thread.currentThread().getName() + "：" + queueLock.getQueueLength() + "人在排队");
				System.out.println(Thread.currentThread().getName() + "： 再等等");
			}
			if(flag)
			{
				Thread.sleep(1000);
			}
		}
		
	}
	
	//writeLock 写锁，排他的，只能一个线程拥有
	//readLock 读锁，可以多个线程共享
	
	public void addOrder() throws InterruptedException {
	
		orderLock.writeLock().lock();
		long writingTime = (long) (Math.random() * 1000);
		Thread.sleep(writingTime);
		System.out.println("老板新加一笔订单");
		orderLock.writeLock().unlock();
	}
	
	//writeLock 写锁，排他的，只能一个线程拥有
	//readLock 读锁，可以多个线程共享
	public void viewOrder() throws InterruptedException {
		orderLock.readLock().lock();
			
		long readingTime = (long) (Math.random() * 500);
		Thread.sleep(readingTime);
		System.out.println(Thread.currentThread().getName() + ": 查看订单本");
		orderLock.readLock().unlock();			

	}
	
	public static void buyMilkTea() throws InterruptedException {
		LockExample lockExample = new LockExample();
		int STUDENTS_CNT = 10;//假设有10个学生
		
		Thread[] students = new Thread[STUDENTS_CNT];
		for (int i = 0; i < STUDENTS_CNT; i++) {
			students[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						long walkingTime = (long) (Math.random() * 1000);
						Thread.sleep(walkingTime);
						lockExample.tryToBuyMilkTea();
					} catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
			);
			
			students[i].start();
		}
		
		for (int i = 0; i < STUDENTS_CNT; i++)
			students[i].join();

	}
	
	
	public static void handleOrder() throws InterruptedException {
		LockExample lockExample = new LockExample();
		
		
		Thread boss = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						lockExample.addOrder();
						long waitingTime = (long) (Math.random() * 1000);
						Thread.sleep(waitingTime);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
		boss.start();

		int workerCnt = 3;//3名员工
		Thread[] workers = new Thread[workerCnt];
		for (int i = 0; i < workerCnt; i++)
		{
			workers[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
								lockExample.viewOrder();
								long workingTime = (long) (Math.random() * 5000);
								Thread.sleep(workingTime);
							} catch (InterruptedException e) {
								System.out.println(e.getMessage());
							}
						}
				}
				
			});
			
			workers[i].start();
		}
		
	}
}
```



**Semaphore**  
**• 信号量，由1965年Dijkstra提出的**  
**• 信号量：本质上是一个计数器**  
**• 计数器大于0，可以使用，等于0不能使用**  
**• 可以设置多个并发量，例如限制10个访问**  
**• Semaphore**  
**– acquire获取**  
**– release释放**  
**• 比Lock更进一步，可以控制多个同时访问关键区**  

```java

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	//这个信号量 5 的意思就是 可以同时有 5个 线程同时 访问这个 关键区域
	private final Semaphore placeSemaphore = new Semaphore(5);
	
	public boolean parking() throws InterruptedException {
		if (placeSemaphore.tryAcquire()) {//获取信号量
			System.out.println(Thread.currentThread().getName() + ": 停车成功");
			return true;
		} else {
			System.out.println(Thread.currentThread().getName() + ": 没有空位");
			return false;
		}

	}
	
	public void leaving() throws InterruptedException {
		placeSemaphore.release();
		System.out.println(Thread.currentThread().getName() + ": 开走");
	}
	
	/**
	 * 现有一地下车库，共有车位5个，由10辆车需要停放，每次停放时，去申请信号量
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		int tryToParkCnt = 10;
		
		SemaphoreExample semaphoreExample = new SemaphoreExample();
		
		Thread[] parkers = new Thread[tryToParkCnt];
		
		for (int i = 0; i < tryToParkCnt; i++) {
			parkers[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						long randomTime = (long) (Math.random() * 1000);
						Thread.sleep(randomTime);
						if (semaphoreExample.parking()) {
							long parkingTime = (long) (Math.random() * 1200);
							Thread.sleep(parkingTime);
							semaphoreExample.leaving();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			parkers[i].start();
		}

		//等待所有的子线程执行完毕，主线程才结束运行
		for (int i = 0; i < tryToParkCnt; i++) {
			parkers[i].join();
		}	
	}
}

```



**Latch(大家都到了，再往下走)**  
**• 等待锁，是一个同步辅助类**  
**• 用来同步执行任务的一个或者多个线程 ** 
**• 不是用来保护临界区或者共享资源 ** 
**• CountDownLatch ** 
**– countDown() 计数减1 ** 
**– await() 等待latch变成0 ** 

```java

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

	/**
	 * 设想百米赛跑比赛 发令枪发出信号后选手开始跑，全部选手跑到终点后比赛结束
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int runnerCnt = 10;//选手的数量 10
		CountDownLatch startSignal = new CountDownLatch(1);//发令枪，启动信号，数量为 1 
		//到终点处 为 10，也就是说到终点处有10个人，没有10个人的话，这个比赛就没有跑完，还有人在跑
		CountDownLatch doneSignal = new CountDownLatch(runnerCnt);

		for (int i = 0; i < runnerCnt; ++i) // create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		System.out.println("准备工作...");
		System.out.println("准备工作就绪");
		//Latch变成0以后，将唤醒所有在此Latch上的await的线程，解锁他们的await等待
		startSignal.countDown(); // let all threads proceed
		System.out.println("比赛开始");
		doneSignal.await(); // wait for all to finish
		System.out.println("比赛结束");
	}

	static class Worker implements Runnable {
		private final CountDownLatch startSignal;
		private final CountDownLatch doneSignal;

		Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		public void run() {
			try {
				//Latch变成0以后，将唤醒所有在此Latch上的await的线程，解锁他们的await等待
				startSignal.await();
				doWork();
				doneSignal.countDown();
			} catch (InterruptedException ex) {
			} // return;
		}

		void doWork() {
			System.out.println(Thread.currentThread().getName() + ": 跑完全程");
		}
	}
}
```

**Barrier**  
**• 集合点，也是一个同步辅助类**  
**• 允许多个线程在某一个点上进行同步**  
**• CyclicBarrier**  
**– 构造函数是需要同步的线程数量**  
**– await等待其他线程，到达数量后，就放行**  

```java


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 适合做归并程序，每个子线程都去算，到最后进行统一处理归并
 * @author YuXiangKaoChi
 *
 */
public class CyclicBarrierExample {
	
	/**
	 * 假定有三行数，用三个线程分别计算每一行的和，最终计算总和
	 * @param args
	 */
	public static void main(String[] args) {
		final int[][] numbers = new int[3][5];
		final int[] results = new int[3];
		int[] row1 = new int[]{1, 2, 3, 4, 5};
		int[] row2 = new int[]{6, 7, 8, 9, 10};
		int[] row3 = new int[]{11, 12, 13, 14, 15};
		numbers[0] = row1;
		numbers[1] = row2;
		numbers[2] = row3;
		
		CalculateFinalResult finalResultCalculator = new CalculateFinalResult(results);
		CyclicBarrier barrier = new CyclicBarrier(3, finalResultCalculator);
		//当有3个线程在barrier上await，就执行finalResultCalculator
		
		for(int i = 0; i < 3; i++) {
			CalculateEachRow rowCalculator = new CalculateEachRow(barrier, numbers, i, results);
			new Thread(rowCalculator).start();
		}		
	}
}

class CalculateEachRow implements Runnable {

	final int[][] numbers;
	final int rowNumber;
	final int[] res;
	final CyclicBarrier barrier;
	
	CalculateEachRow(CyclicBarrier barrier, int[][] numbers, int rowNumber, int[] res) {
		this.barrier = barrier;
		this.numbers = numbers;
		this.rowNumber = rowNumber;
		this.res = res;
	}
	
	@Override
	public void run() {
		int[] row = numbers[rowNumber];
		int sum = 0;
		for (int data : row) {
			sum += data;
			res[rowNumber] = sum;
		}
		try {
			System.out.println(Thread.currentThread().getName() + ": 计算第" + (rowNumber + 1) + "行结束，结果为: " + sum);
			
			/*当在Barrier上await的线程数量
			达到预定的要求后，所有的await的线程不再等待，全部解锁。
			并且，Barrier将执行预定的回调动作（在本程序中，回调动作就是
			CalculateFinalResult)
			*/
			barrier.await(); //等待！只要超过3个(Barrier的构造参数)，就放行。
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}


class CalculateFinalResult implements Runnable {
	final int[] eachRowRes;
	int finalRes;
	public int getFinalResult() {
		return finalRes;
	}
	
	CalculateFinalResult(int[] eachRowRes) {
		this.eachRowRes = eachRowRes;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for(int data : eachRowRes) {
			sum += data;
		}
		finalRes = sum;
		System.out.println("最终结果为: " + finalRes);
	}
}
```



**Phaser**  
**• 允许执行并发多阶段任务，同步辅助类**  
**• 在每一个阶段结束的位置对线程进行同步，当所有的线程都到达这步，再进行下一步**  
**• Phaser**  
**– arrive()**  
**– arriveAndAwaitAdvance()**  

```java

import java.util.concurrent.Phaser;

public class PhaserExample {

	/**
	 * 假设举行考试，总共三道大题，每次下发一道题目，等所有学生完成后再进行下一道
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//定义了 5 个学生
		int studentsCnt = 5;
		//相当于要创建5个线程的 Phaser
		Phaser phaser = new Phaser(studentsCnt);

		for (int i = 0; i < studentsCnt; i++) {
			new Thread(new Student(phaser)).start();//创建了5个线程
		}
	}
}

class Student implements Runnable {

	private final Phaser phaser;

	public Student(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		try {
			doTesting(1);//先做第一题
			phaser.arriveAndAwaitAdvance(); //等到5个线程都到了，才放行
			doTesting(2);//做第二题
			phaser.arriveAndAwaitAdvance();//继续等待，等到5个线程都到了，才放行
			doTesting(3);
			phaser.arriveAndAwaitAdvance();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doTesting(int i) throws InterruptedException {
		String name = Thread.currentThread().getName();
		System.out.println(name + "开始答第" + i + "题");
		long thinkingTime = (long) (Math.random() * 1000);
		Thread.sleep(thinkingTime);
		System.out.println(name + "第" + i + "道题答题结束");
	}
}
```



**Exchanger**  
**• 允许在并发线程中互相交换消息**  
**• 允许在2个线程中定义同步点，当两个线程都到达同步点，它们交换数据结构**  
**• Exchanger**  
**– exchange(), 线程双方互相交互数据**  
**– 交换数据是双向的**  

```java


import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ExchangerExample {
	
	/**
	 * 本例通过Exchanger实现学生成绩查询，简单线程间数据的交换
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Exchanger<String> exchanger = new Exchanger<String>();
		BackgroundWorker worker = new BackgroundWorker(exchanger);
		new Thread(worker).start();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("输入要查询的属性学生姓名：");
			String input = scanner.nextLine().trim();
			//当两个线程，这里的是主线程，同时执行到Exchanger的exchange方法，两个线程就互相交换数据，交换是双向的
			exchanger.exchange(input); //把用户输入传递给线程
			String value = exchanger.exchange(null); //拿到线程反馈结果
			if ("exit".equals(value)) {
				break;
			}
			System.out.println("查询结果：" + value);
		}
		scanner.close();
	} 
}

class BackgroundWorker implements Runnable {

	final Exchanger<String> exchanger;
	BackgroundWorker(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}
	@Override
	public void run() {
		while (true) {
			try {
				//当两个线程，这里的是子线程，同时执行到Exchanger的exchange方法，两个线程就互相交换数据，交换是双向的
				String item = exchanger.exchange(null);
				switch (item) {
				case "zhangsan": 
					exchanger.exchange("90");
					break;
				case "lisi":
					exchanger.exchange("80");
					break;
				case "wangwu":
					exchanger.exchange("70");
					break;
				case "exit":
					exchanger.exchange("exit");
					return;
				default:
					exchanger.exchange("查无此人");
				}					
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
	}		
}
```



**总结**  
**• java.util.concurrent包提供了很多并发编程的控制协作类(共六种)**  
**• 根据业务特点，使用正确的线程并发控制协作**  