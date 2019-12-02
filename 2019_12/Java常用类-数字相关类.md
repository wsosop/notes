## Java常用类

#### **一、Java类库**

------

**–包名以 java 开始的包是 Java 核心包 (Java Core Package)** 
**–包名以 javax 开始的包是 Java 扩展包 (Java Extension Package)** 



#### 二、数字相关类

------

**Java 数字类**
	**–整数 Short, Int, Long**
	**–浮点数 Float, Double**
	**–大数类 BigInteger(大整数), BigDecimal(大浮点数) [这两个类型就不做限制了，内存能表示多大，就可以		表示多大]**
	**–随机数类 Random**
	**–工具类 Math**
**• java.math包**

BigInteger:

```java
import java.math.BigInteger;

public class BigIntegerTest {

	public static void main(String[] args) {
		BigInteger b1 = new BigInteger("123456789"); // 声明BigInteger对象
		BigInteger b2 = new BigInteger("987654321"); // 声明BigInteger对象
		System.out.println("b1: " + b1 +  ", b2:" + b2);
		System.out.println("加法操作：" + b2.add(b1)); // 加法操作
		System.out.println("减法操作：" + b2.subtract(b1)); // 减法操作
		System.out.println("乘法操作：" + b2.multiply(b1)); // 乘法操作
		System.out.println("除法操作：" + b2.divide(b1)); // 除法操作
		System.out.println("最大数：" + b2.max(b1)); // 求出最大数
		System.out.println("最小数：" + b2.min(b1)); // 求出最小数
		BigInteger result[] = b2.divideAndRemainder(b1); // 求出余数的除法操作
		System.out.println("商是：" + result[0] + "；余数是：" + result[1]);
		System.out.println("等价性是：" + b1.equals(b2));
		int flag = b1.compareTo(b2);
		if (flag == -1)
			System.out.println("比较操作: b1<b2");
		else if (flag == 0)
			System.out.println("比较操作: b1==b2");
		else
			System.out.println("比较操作: b1>b2");

	}
}

```

BigDecimal:

```java
import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalTest {
	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("123456789.987654321"); // 声明BigDecimal对象
		BigDecimal b2 = new BigDecimal("987654321.123456789"); // 声明BigDecimal对象
		System.out.println("b1: " + b1 +  ", b2:" + b2);
		System.out.println("加法操作：" + b2.add(b1)); // 加法操作
		System.out.println("减法操作：" + b2.subtract(b1)); // 减法操作
		System.out.println("乘法操作：" + b2.multiply(b1)); // 乘法操作
		//需要指定位数，防止无限循环，或者包含在try-catch中
		System.out.println("除法操作：" + b2.divide(b1,10,BigDecimal.ROUND_HALF_UP)); // 除法操作
		
		System.out.println("最大数：" + b2.max(b1)); // 求出最大数
		System.out.println("最小数：" + b2.min(b1)); // 求出最小数
		
		int flag = b1.compareTo(b2);
		if (flag == -1)
			System.out.println("比较操作: b1<b2");
		else if (flag == 0)
			System.out.println("比较操作: b1==b2");
		else
			System.out.println("比较操作: b1>b2");
		
		System.out.println("===================");
		
		//尽量采用字符串赋值
		System.out.println(new BigDecimal("2.3"));
		System.out.println(new BigDecimal(2.3));
		
		System.out.println("===================");
		
		BigDecimal num1 = new BigDecimal("10");
		BigDecimal num2 = new BigDecimal("3");
		//需要指定位数，防止无限循环，或者包含在try-catch中
        //这句话的意思是四舍五入，并且是保留三个小数
		BigDecimal num3 = num1.divide(num2, 3, BigDecimal.ROUND_HALF_UP);
		System.out.println(num3);
	}
}

```



#### Random：

**Random 随机数**
**–nextInt() 返回一个随机int**
**–nextInt(int a) 返回一个[0,a)之间的随机int**
**–nextDouble()返回一个[0.0,1.0]之间double**
**–ints 方法批量返回随机数数组**
**• Math.random() 返回一个[0.0,1.0]之间double**
**• 查看RandomTest.java**

```java
package com.wck;

import java.util.Random;

/**
 * @author 御香烤翅
 * @create 2019-12-02 16:48
 */
public class Test {

    public static void main(String[] args) {

        //第一种办法，采用Random类 随机生成在int范围内的随机数
        Random rd = new Random();
        System.out.println(rd.nextInt());
        System.out.println(rd.nextInt(100)); //0--100的随机数
        System.out.println(rd.nextLong());
        System.out.println(rd.nextDouble());
        System.out.println("1.=========================");

        //第二种，生成一个范围内的随机数 例如0到时10之间的随机数
        //Math.random[0,1)
        System.out.println(Math.round(Math.random()*10));
        System.out.println("2.=========================");


        //JDK 8 新增方法
        rd.ints();  //返回无限个int类型范围内的数据
        int[] arr = rd.ints(10).toArray();  //生成10个int范围类的个数。
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("3.=========================");

        arr = rd.ints(5, 10, 100).toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("4.=========================");

        arr = rd.ints(10).limit(5).toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}

//输出参考
// 1108388778
// 76
// 1712712714548749485
// 0.7381458254056171
// 1.=========================
// 4
// 2.=========================
// 950600132
// -1643507319
// 1544298346
// 877997876
// -813724640
// -186261074
// 1290209345
// 576733381
// -1934198441
// -1059208273
// 3.=========================
// 63
// 76
// 40
// 54
// 52
// 4.=========================
// -581911904
// 701963666
// 2118751198
// 2003820311
// 755942738
```



#### java.lang.Math

​	**–绝对值函数abs**
​	**–对数函数log**
​	**–比较函数max、min**
​	**–幂函数pow**
​	**–四舍五入函数round等**
​	**–向下取整floor**
​	**–向上取整ceil**

**• 查看MathTest.java**

```java
package com.wck;

import java.util.Random;

/**
 * @author 御香烤翅
 * @create 2019-12-02 16:48
 */
public class Test {

    public static void main(String[] args) {
                System.out.println(Math.abs(-5));    //绝对值
                System.out.println(Math.max(-5,-8)); //最大值
                System.out.println(Math.pow(-5,2));  //求幂
                System.out.println(Math.round(3.5)); //四舍五入
                System.out.println(Math.ceil(3.5));  //向上取整
                System.out.println(Math.floor(3.5)); //向下取整
            }

        }

//输出参考
//5
//-5
//25.0
//4
//4.0
//3.0


```

