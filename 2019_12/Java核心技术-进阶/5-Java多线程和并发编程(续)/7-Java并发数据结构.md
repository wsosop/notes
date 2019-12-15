## Java并发数据结构

**并发数据结构(1)**  
**• 常用的数据结构是线程不安全的**  
**– ArrayList, HashMap, HashSet 非同步的**  
**– 多个线程同时读写，可能会抛出异常或数据错误**  
**• 传统Vector，Hashtable等同步集合性能过差 ** 
**• 并发数据结构：数据添加和删除**  
**– 阻塞式集合：当集合为空或者满时，等待 ** 
**– 非阻塞式集合：当集合为空或者满时，不等待，返回null或异常 ** 



**并发数据结构(2)**  
**• List**  
**– Vector 同步安全，写多读少**  
**– ArrayList 不安全**  
**– <u>Collections.synchronizedList(List list) 基于synchronized，效率差</u>**  
**– <u>CopyOnWriteArrayList 读多写少，基于复制机制，非阻塞</u>**  
**• Set**   
**– HashSet 不安全**  
**– <u>Collections.synchronizedSet(Set set) 基于synchronized，效率差</u>**  
**– <u>CopyOnWriteArraySet (基于CopyOnWriteArrayList实现) 读多写少，非阻塞</u>**  



**并发数据结构(3)**  
**• Map**   
**– Hashtable 同步安全，写多读少**  
**– HashMap 不安全**  
**– <u>Collections.synchronizedMap(Map map) 基于synchronized，效率差</u>**  
**– <u>ConcurrentHashMap 读多写少，非阻塞</u>**   
**• Queue & Deque (队列，JDK 1.5 提出)**   
**– <u>ConcurrentLinkedQueue 非阻塞</u>**  
**– <u>ArrayBlockingQueue/LinkedBlockingQueue 阻塞</u>**  



```java
└─src
    ├─list
    │      ListTest.java
    │      
    ├─map
    │      MapTest.java
    │      
    ├─queue
    │      QueueTest.java
    │      
    └─set
            SetTest.java
```

ListTest：

```java
package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {    
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        List<String> unsafeList = new ArrayList<String>();
        //线程安全
        List<String> safeList1 = Collections.synchronizedList(new ArrayList<String>());
        //线程安全
        CopyOnWriteArrayList<String> safeList2 = new CopyOnWriteArrayList<String>();

        ListThread t1 = new ListThread(unsafeList);
        ListThread t2 = new ListThread(safeList1);
        ListThread t3 = new ListThread(safeList2);

        for(int i = 0; i < 10; i++){
            Thread t = new Thread(t1, String.valueOf(i));
            t.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread t = new Thread(t2, String.valueOf(i));
            t.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread t = new Thread(t3, String.valueOf(i));
            t.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("listThread1.list.size() = " + t1.list.size());
        System.out.println("listThread2.list.size() = " + t2.list.size());
        System.out.println("listThread3.list.size() = " + t3.list.size());

        //输出list中的值
        System.out.println("unsafeList：");
        for(String s : t1.list){
            if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
        System.out.println();
        System.out.println("safeList1：");
        for(String s : t2.list){
        	if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
        System.out.println();
        System.out.println("safeList2：");
        for(String s : t3.list){
        	if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
    }
}

class ListThread implements Runnable{
	public List<String> list;

    public ListThread(List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
    	int i = 0;
    	while(i<10)
    	{
    		try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //把当前线程名称加入list中
            list.add(Thread.currentThread().getName());
            i++;
    	}        
    }
}
/*
输出结果：
listThread1.list.size() = 83
listThread2.list.size() = 100
listThread3.list.size() = 100
unsafeList：
4  3  0  1  9  8  7  6  5  4  null  0  2  6  5  7  null  9  4  2  8  9  7  6  4  1  0  2  6  9  5  7  8  3  1  2  0  8  5  6  7  9  3  0  2  8  5  6  7  9  3  2  1  0  6  7  9  5  8  4  1  0  8  5  9  7  6  3  1  6  7  5  8  9  0  4  1  2  5  8  7  6  9  
safeList1：
4  2  3  0  1  8  6  9  7  5  4  1  0  3  2  6  8  5  9  7  0  4  3  2  1  6  8  7  9  5  4  1  2  3  0  8  6  5  9  7  1  4  3  2  0  6  8  7  5  9  1  4  3  2  0  6  8  7  5  9  4  1  2  0  3  6  8  9  5  7  4  1  3  8  6  0  2  7  5  9  1  4  3  6  8  9  5  7  2  0  4  1  3  8  6  0  2  7  5  9  
safeList2：
3  5  7  0  6  2  1  4  9  8  1  2  4  6  0  7  3  5  9  8  0  4  6  2  3  5  1  7  8  9  6  2  1  7  5  4  0  3  9  8  3  2  5  7  0  4  1  6  8  9  3  2  0  1  4  6  7  5  8  9  7  1  0  3  6  5  4  2  9  8  2  4  5  3  0  1  7  6  8  9  4  5  2  6  1  3  8  7  9  0  4  5  2  1  7  0  6  3  9  8  
*/
```

SetTest：

```java
package set;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest{  
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Set<String> unsafeSet = new HashSet<String>();
        //线程安全
        Set<String> safeSet1 = Collections.synchronizedSet(new HashSet<String>());
        //线程安全
        CopyOnWriteArraySet<String> safeSet2 = new CopyOnWriteArraySet<String>();

        SetThread t1 = new SetThread(unsafeSet);
        SetThread t2 = new SetThread(safeSet1);
        SetThread t3 = new SetThread(safeSet2);

        //unsafeSet的运行测试
        for(int i = 0; i < 10; i++){
        	Thread t = new Thread(t1, String.valueOf(i));
        	t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t2, String.valueOf(i));
            t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t3, String.valueOf(i));
            t.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("setThread1.set.size() = " + t1.set.size());
        System.out.println("setThread2.set.size() = " + t2.set.size());
        System.out.println("setThread3.set.size() = " + t3.set.size());

        //输出set中的值
        System.out.println("unsafeSet：");
        for(String element:t1.set){
            if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
        System.out.println();
        System.out.println("safeSet1：");
        for(String element:t2.set){
        	if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
        System.out.println();
        System.out.println("safeSet2：");
        for(String element:t3.set){
        	if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
    }
}

class SetThread implements Runnable{
	public Set<String> set;

    public SetThread(Set<String> set){
        this.set = set;
    }

    @Override
    public void run() {
    	int i = 0;
    	while(i<10)
    	{
    		i++;
    		try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //把当前线程名称加入list中
            set.add(Thread.currentThread().getName() + i);
    	}        
    }
}
/*
输出结果：
setThread1.set.size() = 88
setThread2.set.size() = 100
setThread3.set.size() = 100
unsafeSet：
44  88  01  45  89  02  46  03  47  04  48  05  49  06  07  08  09  110  310  510  710  91  910  92  93  94  51  95  52  96  53  97  54  98  55  99  12  56  13  57  14  58  15  59  16  17  18  19  61  62  63  64  21  65  22  66  23  67  24  68  25  69  26  27  28  29  010  210  410  610  810  71  72  73  74  75  32  76  33  77  34  78  35  79  36  37  38  39  81  82  83  84  41  85  42  86  43  87  
safeSet1：
88  01  89  02  03  04  05  06  07  08  09  110  510  91  910  92  93  94  95  96  97  98  11  99  12  13  14  15  16  17  18  19  21  22  23  24  25  26  27  28  29  010  410  810  31  32  33  34  35  36  37  38  39  41  42  43  44  45  46  47  48  49  310  710  51  52  53  54  55  56  57  58  59  61  62  63  64  65  66  67  68  69  210  610  71  72  73  74  75  76  77  78  79  81  82  83  84  85  86  87  
safeSet2：
01  81  91  71  51  41  31  61  21  11  02  82  92  72  52  42  32  62  22  12  03  13  23  63  33  43  53  73  93  83  04  84  44  64  14  74  94  54  34  24  05  35  95  55  25  75  85  15  65  45  06  26  56  86  96  76  36  66  46  16  07  37  47  67  57  17  77  87  97  27  08  98  28  78  58  68  38  48  18  88  09  99  29  79  59  69  19  49  89  39  010  910  210  710  610  510  410  810  110  310  
*/
```

MapTest：

```java
package map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest{    
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Map<Integer,String> unsafeMap = new HashMap<Integer,String>();
        //线程安全
        Map<Integer,String> safeMap1 = Collections.synchronizedMap(new HashMap<Integer,String>());
        //线程安全
        ConcurrentHashMap<Integer,String> safeMap2 = new ConcurrentHashMap<Integer,String>();

        MapThread t1 = new MapThread(unsafeMap);
        MapThread t2 = new MapThread(safeMap1);
        MapThread t3 = new MapThread(safeMap2);

        //unsafeMap的运行测试
        for(int i = 0; i < 10; i++){
        	Thread t = new Thread(t1);
        	t.start();
        }       
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t2);
            t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t3);
            t.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("mapThread1.map.size() = " + t1.map.size());
        System.out.println("mapThread2.map.size() = " + t2.map.size());
        System.out.println("mapThread3.map.size() = " + t3.map.size());

        //输出set中的值
        System.out.println("unsafeMap：");
        Iterator iter = t1.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }
        System.out.println();
        
        System.out.println("safeMap1：");
        iter = t2.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }

        System.out.println();
        System.out.println("safeMap2：");
        iter = t3.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }
        System.out.println();
        System.out.println("mapThread1.map.size() = " + t1.map.size());
        System.out.println("mapThread2.map.size() = " + t2.map.size());
        System.out.println("mapThread3.map.size() = " + t3.map.size());
    }
}

class MapThread implements Runnable
{
	public Map<Integer,String> map;

    public MapThread(Map<Integer,String> map){
        this.map = map;
    }

    @Override
    public void run() {
        int i=0;
        
        while(i<100)
        {
        	//把当前线程名称加入map中
            map.put(i++,Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }        
    }
}
/*
输出结果：
mapThread1.map.size() = 101
mapThread2.map.size() = 100
mapThread3.map.size() = 100
unsafeMap：
64:Thread-6 65:Thread-5 66:Thread-2 67:Thread-8 68:Thread-8 69:Thread-2 70:Thread-5 71:Thread-2 8:Thread-4 72:Thread-6 9:Thread-2 73:Thread-5 10:Thread-0 74:Thread-2 11:Thread-4 75:Thread-5 12:Thread-0 76:Thread-2 13:Thread-7 77:Thread-8 14:Thread-0 78:Thread-8 15:Thread-4 79:Thread-6 16:Thread-5 80:Thread-8 17:Thread-6 81:Thread-6 18:Thread-0 82:Thread-5 19:Thread-5 83:Thread-5 20:Thread-5 84:Thread-6 21:Thread-8 85:Thread-5 22:Thread-5 86:Thread-0 23:Thread-8 87:Thread-5 24:Thread-0 88:Thread-5 25:Thread-5 89:Thread-2 26:Thread-8 90:Thread-6 27:Thread-5 91:Thread-0 28:Thread-6 92:Thread-6 29:Thread-5 93:Thread-6 30:Thread-5 94:Thread-5 31:Thread-6 95:Thread-6 32:Thread-5 96:Thread-8 33:Thread-6 97:Thread-6 34:Thread-0 98:Thread-6 35:Thread-0 99:Thread-0 50:Thread-2 51:Thread-6 52:Thread-5 53:Thread-6 54:Thread-2 55:Thread-6 56:Thread-2 60:Thread-5 61:Thread-0 62:Thread-5 63:Thread-5 36:Thread-6 37:Thread-8 38:Thread-2 39:Thread-5 40:Thread-2 41:Thread-6 42:Thread-2 43:Thread-6 44:Thread-0 45:Thread-0 46:Thread-8 47:Thread-2 48:Thread-8 49:Thread-2 57:Thread-6 58:Thread-5 59:Thread-2 
safeMap1：
0:Thread-19 1:Thread-12 2:Thread-19 3:Thread-12 4:Thread-18 5:Thread-19 6:Thread-14 7:Thread-19 8:Thread-15 9:Thread-12 10:Thread-12 11:Thread-15 12:Thread-12 13:Thread-10 14:Thread-19 15:Thread-19 16:Thread-10 17:Thread-14 18:Thread-19 19:Thread-10 20:Thread-19 21:Thread-14 22:Thread-12 23:Thread-13 24:Thread-14 25:Thread-12 26:Thread-14 27:Thread-12 28:Thread-14 29:Thread-12 30:Thread-12 31:Thread-15 32:Thread-12 33:Thread-14 34:Thread-12 35:Thread-12 36:Thread-14 37:Thread-12 38:Thread-17 39:Thread-10 40:Thread-19 41:Thread-13 42:Thread-10 43:Thread-13 44:Thread-10 45:Thread-13 46:Thread-10 47:Thread-13 48:Thread-13 49:Thread-12 50:Thread-11 51:Thread-12 52:Thread-13 53:Thread-13 54:Thread-12 55:Thread-15 56:Thread-12 57:Thread-11 58:Thread-11 59:Thread-11 60:Thread-11 61:Thread-11 62:Thread-11 63:Thread-11 64:Thread-11 65:Thread-11 66:Thread-11 67:Thread-11 68:Thread-11 69:Thread-11 70:Thread-11 71:Thread-11 72:Thread-11 73:Thread-11 74:Thread-11 75:Thread-11 76:Thread-11 77:Thread-11 78:Thread-11 79:Thread-11 80:Thread-11 81:Thread-11 82:Thread-11 83:Thread-11 84:Thread-11 85:Thread-11 86:Thread-15 87:Thread-11 88:Thread-15 89:Thread-16 90:Thread-15 91:Thread-11 92:Thread-11 93:Thread-15 94:Thread-16 95:Thread-11 96:Thread-15 97:Thread-11 98:Thread-11 99:Thread-16 
safeMap2：
0:Thread-25 1:Thread-23 2:Thread-25 3:Thread-25 4:Thread-23 5:Thread-24 6:Thread-25 7:Thread-23 8:Thread-25 9:Thread-20 10:Thread-20 11:Thread-20 12:Thread-20 13:Thread-20 14:Thread-20 15:Thread-20 16:Thread-20 17:Thread-20 18:Thread-20 19:Thread-20 20:Thread-20 21:Thread-20 22:Thread-20 23:Thread-20 24:Thread-20 25:Thread-20 26:Thread-20 27:Thread-20 28:Thread-20 29:Thread-20 30:Thread-20 31:Thread-20 32:Thread-20 33:Thread-20 34:Thread-20 35:Thread-20 36:Thread-20 37:Thread-20 38:Thread-20 39:Thread-20 40:Thread-20 41:Thread-20 42:Thread-20 43:Thread-20 44:Thread-20 45:Thread-20 46:Thread-20 47:Thread-20 48:Thread-20 49:Thread-20 50:Thread-20 51:Thread-20 52:Thread-20 53:Thread-20 54:Thread-20 55:Thread-20 56:Thread-20 57:Thread-20 58:Thread-20 59:Thread-20 60:Thread-20 61:Thread-20 62:Thread-20 63:Thread-20 64:Thread-20 65:Thread-20 66:Thread-20 67:Thread-20 68:Thread-20 69:Thread-20 70:Thread-20 71:Thread-20 72:Thread-20 73:Thread-20 74:Thread-20 75:Thread-20 76:Thread-20 77:Thread-20 78:Thread-20 79:Thread-20 80:Thread-20 81:Thread-20 82:Thread-20 83:Thread-20 84:Thread-20 85:Thread-20 86:Thread-20 87:Thread-20 88:Thread-20 89:Thread-20 90:Thread-20 91:Thread-20 92:Thread-20 93:Thread-20 94:Thread-20 95:Thread-20 96:Thread-20 97:Thread-20 98:Thread-20 99:Thread-20 
mapThread1.map.size() = 101
mapThread2.map.size() = 100
mapThread3.map.size() = 100

*/
```

QueueTest：

```Java
package queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Deque<String> unsafeQueue = new ArrayDeque<String>();
        //线程安全
        ConcurrentLinkedDeque<String> safeQueue1 = new ConcurrentLinkedDeque<String>();

        ArrayBlockingQueue<String> safeQueue2 = new ArrayBlockingQueue<String>(100);

        QueueThread t1 = new QueueThread(unsafeQueue);
        QueueThread t2 = new QueueThread(safeQueue1);
        QueueThread t3 = new QueueThread(safeQueue2);

        for(int i = 0; i < 10; i++){
            Thread thread1 = new Thread(t1, String.valueOf(i));
            thread1.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread thread2 = new Thread(t2, String.valueOf(i));
            thread2.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread thread3 = new Thread(t3, String.valueOf(i));
            thread3.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("queueThread1.queue.size() = " + t1.queue.size());
        System.out.println("queueThread2.queue.size() = " + t2.queue.size());
        System.out.println("queueThread3.queue.size() = " + t3.queue.size());

        //输出queue中的值
        System.out.println("unsafeQueue：");
        for(String s:t1.queue)
        {
        	System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("safeQueue1：");
        for(String s:t2.queue)
        {
        	System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("safeQueue2：");
        for(String s:t3.queue)
        {
        	System.out.print(s + " ");
        }
    }
}

class QueueThread implements Runnable{
	public Queue<String> queue;

    public QueueThread(Queue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
    	int i = 0;
    	while(i<10)
    	{
    		i++;
    		try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //把当前线程名称加入list中
            queue.add(Thread.currentThread().getName());
    	}        
    }
}
/*
输出结果：
queueThread1.queue.size() = 72
queueThread2.queue.size() = 100
queueThread3.queue.size() = 100
unsafeQueue：
7 3 8 5 4 9 2 3 4 5 7 8 9 1 3 6 4 9 1 0 3 6 5 8 4 9 0 1 2 3 6 5 8 4 7 9 2 3 6 5 4 7 8 9 2 3 4 5 7 9 1 5 7 4 8 3 9 2 7 6 4 8 3 9 2 Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayDeque$DeqIterator.next(ArrayDeque.java:643)
	at queue.QueueTest.main(QueueTest.java:44)

*/
```



**总结**  
**• 了解数据结构并发读写的问题**  
**• 根据业务特点，使用正确的并发数据结构**  

