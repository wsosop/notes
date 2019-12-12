## 列表List

**• List：列表**  
**–有序的Collection**  
**–允许重复元素**  
**–{1，2，4，{5，2}，1，3}**  
**• List 主要实现**  
**–ArrayList(非同步的)不是线程安全的**  
**–LinkedList(非同步)不是线程安全的**   
**–Vector(同步)是线程安全的**  

------

**• ArrayList：**  
**–以数组实现的列表，不支持同步**  
<u>**• List list = Collections.synchronizedList(new ArrayList(...));**</u>  
**–利用索引位置可以快速定位访问**  
**–不适合指定位置的插入、删除操作**  
**–适合变动不大，主要用于查询的数据**  
**–和Java数组相比，其容量是可动态调整的**  
**–ArrayList在元素填满容器时会自动扩充容器大小的50%**  
**–查看程序ArrayListTest.java**  

```java
package com.wck;

import java.util.ArrayList;
import java.util.Iterator;
//Vector 几乎和ArrayList一样，除了Vector本身是同步的

public class ArrayListTest {
	public static void main(String[] a) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(3);
		al.add(2);
		al.add(1);
		al.add(4);
		al.add(5);
		al.add(6);
		al.add(new Integer(6));

		System.out.print("The third element is  ");
		System.out.println(al.get(3));
		al.remove(3);  //删除第四个元素，后面元素往前挪动
		al.add(3, 9);  //将9插入到第4个元素，后面元素往后挪动

		System.out.println("======遍历方法=============");

		ArrayList<Integer> as = new ArrayList<Integer>(100000);
		for (int i=0; i<100000; i++)
		{
			as.add(i);
		}
		traverseByIterator(as);
		traverseByIndex(as);
		traverseByFor(as);
	}
	public static void traverseByIterator(ArrayList<Integer> al)
	{
		long startTime = System.nanoTime();
		System.out.println("============迭代器遍历==============");
		Iterator<Integer> iter1 = al.iterator();
		while(iter1.hasNext()){
			iter1.next();
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
	public static void traverseByIndex(ArrayList<Integer> al)
	{
		long startTime = System.nanoTime();
		System.out.println("============随机索引值遍历==============");
		for(int i=0;i<al.size();i++)
		{
			al.get(i);
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
	public static void traverseByFor(ArrayList<Integer> al)
	{
		long startTime = System.nanoTime();
		System.out.println("============for循环遍历==============");
		for(Integer item : al)
		{
			;
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
}

/*输出结果 这个结果说明 随机索引值最快 其次 是迭代器遍历 最后是 for循环遍历 但是只是相对的                 
The third element is  4
======遍历方法=============
============迭代器遍历==============
16232700纳秒
============随机索引值遍历==============
8740800纳秒
============for循环遍历==============
23091000纳秒
*/

```



**• LinkedList：**  
**–以双向链表实现的列表，不支持同步**  
**• List list = Collections.synchronizedList(new LinkedList(...));**  
**–可被当作堆栈、队列和双端队列进行操作**  
**–顺序访问高效，随机访问较差，中间插入和删除高效 **   
**–适用于经常变化的数据**  
**–查看程序LinkedListTest.java**  

```java
package com.wck;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(3);
		ll.add(2);
		ll.add(5);
		ll.add(6);
		ll.add(6);
		System.out.println(ll.size());
		ll.addFirst(9);  //在头部增加9

		for(Integer item : ll)
		{
			System.out.println("item:"+item);
		}


		ll.add(3, 10);   //将10插入到第四个元素，四以及后续的元素往后挪动
		ll.remove(4);    //将第五个元素删除

		System.out.println("-----------------------");
		for(Integer item : ll)
		{
			System.out.println("item:"+item);
		}

		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i=0; i<100000; i++)
		{
			list.add(i);
		}
		traverseByIterator(list);
		traverseByIndex(list);
		traverseByFor(list);

	}

	public static void traverseByIterator(LinkedList<Integer> list)
	{
		long startTime = System.nanoTime();
		System.out.println("============迭代器遍历==============");
		Iterator<Integer> iter1 = list.iterator();
		while(iter1.hasNext()){
			iter1.next();
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
	public static void traverseByIndex(LinkedList<Integer> list)
	{
		long startTime = System.nanoTime();
		System.out.println("============随机索引值遍历==============");
		for(int i=0;i<list.size();i++)
		{
			list.get(i);
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
	public static void traverseByFor(LinkedList<Integer> list)
	{
		long startTime = System.nanoTime();
		System.out.println("============for循环遍历==============");
		for(Integer item : list)
		{
			;
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
}

/*结果表明：使用迭代器和for循环的时间都在一个数量级的，使用随机索引值遍历的时间最慢，且相差100倍
5
item:9
item:3
item:2
item:5
item:6
item:6
-----------------------
item:9
item:3
item:2
item:10
item:6
item:6
============迭代器遍历==============
11792500纳秒
============随机索引值遍历==============
5507453500纳秒
============for循环遍历==============
10409201纳秒

*/
```



接下来，对比ArrayList 和 LinkList 这两个List 在一万个数据之中的访问添加，删除的情况

```java
package com.wck;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListCompareTest {

	public static void main(String[] args) {
		int times = 10 * 1000;//一万个数据
	    // times = 100 * 1000;
	    // times = 1000 * 1000;
	    
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
	    LinkedList<Integer> linkedList = new LinkedList<Integer>();

	    System.out.println("Test times = " + times);
	    System.out.println("-------------------------");
	    
	    // ArrayList add
	    long startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
            //这里是在头部添加的数据，ArrayList在头部添加数据的时候，原数据会依次的向后进行移动
	        arrayList.add(0,i);//这种的添加的效率会很差
	    }
	    long endTime = System.nanoTime();
	    long duration = endTime - startTime;
	    System.out.println(duration + " <--ArrayList add");

	    // LinkedList add
	    startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
	        linkedList.add(0,i);//由于LinkedList是双向链表，所以插入会很方便，效率会很高
	    }
	    endTime = System.nanoTime();
	    duration = endTime - startTime;
	    System.out.println(duration + " <--LinkedList add");
	    System.out.println("-------------------------");
	    
	    // ArrayList get
	    startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
	        arrayList.get(i);//ArrayList根据索引进行访问的效率会很高，因为本身就是数组来实现的
	    }
	    endTime = System.nanoTime();
	    duration = endTime - startTime;
	    System.out.println(duration + " <--ArrayList get");

	    // LinkedList get
	    startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
	        linkedList.get(i);//LinkedList根据索引来遍历get的效率会差
	    }
	    endTime = System.nanoTime();
	    duration = endTime - startTime;
	    System.out.println(duration + " <--LinkedList get");
	    System.out.println("-------------------------");

	    // ArrayList remove
	    startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
	        arrayList.remove(0);//和插入一样，每次删除头结点的，后面的数据会依次向前移动，效率差
	    }
	    endTime = System.nanoTime();
	    duration = endTime - startTime;
	    System.out.println(duration + " <--ArrayList remove");

	    // LinkedList remove
	    startTime = System.nanoTime();

	    for (int i = 0; i < times; i++) {
	        linkedList.remove(0);//只需要删除节点，效率高
	    }
	    endTime = System.nanoTime();
	    duration = endTime - startTime;
	    System.out.println(duration + " <--LinkedList remove");
	}
}
/*
Test times = 10000
-------------------------
14267500 <--ArrayList add
5015800 <--LinkedList add
-------------------------
1787000 <--ArrayList get
59794900 <--LinkedList get
-------------------------
13063200 <--ArrayList remove
1412600 <--LinkedList remove
*/
```



**• Vector(同步)**  
**–和ArrayList类似，可变数组实现的列表，是使用数组进行实现的**  
**–<u>Vector同步，适合在多线程下使用</u>**  
**–原先不属于JCF框架，属于Java最早的数据结构，性能较差**  
**–从JDK1.2开始，Vector被重写，并纳入到JCF**  
**<u>–官方文档建议在非同步情况下，优先采用ArrayList</u>**  
**–查看程序VectorTest.java**  

```java
package com.wck;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		v.remove(2);
		v.add(1, 5);
		System.out.println(v.size());

		System.out.println("======遍历方法=============");

		Vector<Integer> v2 = new Vector<Integer>(100000);
		for (int i = 0; i < 100000; i++) {
			v2.add(i);
		}
		traverseByIterator(v2);
		traverseByIndex(v2);
		traverseByFor(v2);
		traverseByEnumeration(v2);
	}

	public static void traverseByIterator(Vector<Integer> v) {
		long startTime = System.nanoTime();
		System.out.println("============迭代器遍历==============");
		Iterator<Integer> iter1 = v.iterator();
		while (iter1.hasNext()) {
			iter1.next();
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}

	public static void traverseByIndex(Vector<Integer> v) {
		long startTime = System.nanoTime();
		System.out.println("============随机索引值遍历==============");
		for (int i = 0; i < v.size(); i++) {
			v.get(i);
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}

	public static void traverseByFor(Vector<Integer> v) {
		long startTime = System.nanoTime();
		System.out.println("============for循环遍历==============");
		for (Integer item : v) {
			;
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}

	public static void traverseByEnumeration(Vector<Integer> v) {
		long startTime = System.nanoTime();
		System.out.println("============Enumeration遍历==============");
		for (Enumeration<Integer> enu = v.elements(); enu.hasMoreElements();) {
			enu.nextElement();
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println(duration + "纳秒");
	}
}

/* 结果表明：和arrayList的遍历结果差不多。但是性能会比ArrayList的差些，因为是同步的
3
======遍历方法=============
============迭代器遍历==============
19152100纳秒
============随机索引值遍历==============
10775600纳秒
============for循环遍历==============
12799800纳秒
============Enumeration遍历==============
12263100纳秒
*/

```



**• 总结**  
**–ArrayList/LinkedList/Vector**  
**–同步采用Vector**  
**–非同步情况下，根据数据操作特点选取ArrayList/LinkedList**  

