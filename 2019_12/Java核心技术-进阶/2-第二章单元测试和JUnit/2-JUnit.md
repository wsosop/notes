## JUnit

**JUnit简介**
**• JUnit：一个Java语言的单元测试框架**
**–Kent Beck (极限编程) 和 Erich Gamma (设计模式)建立的**
**–是xUnit家族中最成功的一个**
**–大部分的Java IDE都集成了JUnit作为单元测试工具**
**–官网： https://junit.org**
**–版本**
**• 5 ：最后稳定版5.3.2， 2018.11发布(JDK8及以上)**
**• 4 ：最后稳定版4.12, 2014.12发布**
**• 3 ：最后稳定版3.8.2, 2007.5发布**



使用：

```java
├─src
│  ├─main
│  │  ├─java
│  │  │      Triangle.java
│  │  │      
│  │  └─resources
│  └─test
│      ├─java
│      │      TriangleTest.java
│      │      
│      └─resources
```

```java

public class Triangle {	
	public boolean judgeEdges(int a, int b, int c)
	{
		boolean result = true;
		
		//边长非负性
		if(a<=0 || b<=0 || c<=0) {
			return false;
		}
		
		//两边和大于第三边
		if(a+b<=c) {
			result = false;
		}
		if(b+c<=a) {
			result = false;
		}
		if(c+a<=b) {
			result = false;
		}
		return result;
	}	
}

```

```java


import static org.junit.Assert.*; //导入Assert类的所有静态方法，自JDK1.5引入

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void test() {
        //输出的结果是否等同于 false 这个结果
		assertEquals(true, new Triangle().judgeEdges(1,2,3));
		//Assert.assertEquals(false, new Triangle().judgeEdges(1,2,3));
	}

}

```

