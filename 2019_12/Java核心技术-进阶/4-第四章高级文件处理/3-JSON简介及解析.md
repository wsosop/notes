## JSON简介及解析

**JSON概念**  
**• JSON**  
**– JavaScript Object Notation, JS 对象表示法**  
**– 是一种轻量级的数据交换格式**  
**– 类似XML，更小、更快、更易解析**  
**– 最早用于Javascript中(2002年)，容易解析，最后推广到全语言**  
**– 尽管使用Javascript语法，但是独立于编程语言**  



**JSONObject和JSONArray**  
**• 名称/值对。如"firstName":"John"**  
**– JSON对象：{“name":"Jo","email":"a@b.com"}**  
**– 数据在键值对中**  
**– 数据由逗号分隔**  
**– 花括号保存对象**  
**• JSON数组**  
**– 方括号保存数组**  
**[{“name":"Jo","email":"a@b.com"}, {“name":"Jo","email":"a@b.com"}]**  



**Java的JSON处理**  
**• org.json：JSON官方推荐的解析类**  
**– 简单易用，通用性强**  
**– 复杂功能欠缺**  
**• GSON：Google出品**  
**– 基于反射，可以实现JSON对象、JSON字符串和Java对象互转**  
**• Jackson：号称最快的JSON处理器**  
**– 简单易用，社区更新和发布速度比较快**  



**JSON 主要用途**  
**• JSON生成**  
**• JSON解析**  
**• JSON校验**  
**• 和Java Bean对象进行互解析**  
**– 具有一个无参的构造函数**  
**– 可以包括多个属性，所有属性都是private**  
**– 每个属性都有相应的Getter/Setter方法**  
**– Java Bean用于封装数据，又可称为POJO(Plain Old Java Object)解释为：Java普通对象**  



```java
├─src
│  ├─main
│  │  ├─java
│  │  │      Book.java
│  │  │      Book0.java
│  │  │      GsonTest.java
│  │  │      JacksonTest.java
│  │  │      OrgJsonTest.java
│  │  │      Person.java
│  │  │      
│  │  └─resources
│  │          first.json
│  │          
│  └─test
│      ├─java
│      └─resources
│  books.json
│  books2.json
```

```java


public class Book {
    private String category;
    private String title;
    private String author;
    private String year;
    private int price;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [category=" + category + ", title=" + title + ", author=" + author 
				+ ", year=" + year + ", price=" + price + "]";
	}   
}


```

```java
import java.util.List;

public class Person {	
	private String name;
	private int age;
	private List<Integer> scores;
	
	public Person(){		
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
}
```

```java


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 采用org.json包来解析JSON
 * @author Tom
 *
 */

public class OrgJsonTest {
	public static void main(String[] args) {
		testJsonObject();
		System.out.println("=========华丽丽的分割线==============");
		testJsonFile();
	}
    public static void testJsonObject() {
    	//构造对象
    	Person p = new Person();
    	p.setName("Tom");
    	p.setAge(20);
    	p.setScores(Arrays.asList(60,70,80));
    	
    	
    	//构造JSONObject对象
    	JSONObject obj = new JSONObject(); 	
    	
        //string
    	obj.put("name", p.getName());
        //int
    	obj.put("age", p.getAge());
        //array
        obj.put("scores", p.getScores());
        //null
        //object.put("null", null);  不可以放入 null 进去
        System.out.println(obj);        
        
        System.out.println("name: " + obj.getString("name"));
        System.out.println("age: " + obj.getInt("age"));
        System.out.println("scores: " + obj.getJSONArray("scores"));
    }

    public static void testJsonFile() {
    	File file = new File("books.json");
        try (FileReader reader = new FileReader(file)) {
        	//读取文件内容到JsonObject对象中
            int fileLen = (int) file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String s = String.valueOf(chars);
            JSONObject jsonObject = new JSONObject(s);
            
            //开始解析JSONObject对象
            JSONArray books = jsonObject.getJSONArray("books");
            List<Book> bookList = new ArrayList<>();
            for (Object book : books) {
            	//获取单个JSONObject对象
                JSONObject bookObject = (JSONObject) book;
                Book book1 = new Book();
                book1.setAuthor(bookObject.getString("author"));
                book1.setYear(bookObject.getString("year"));
                book1.setTitle(bookObject.getString("title"));
                book1.setPrice(bookObject.getInt("price"));
                book1.setCategory(bookObject.getString("category"));
                bookList.add(book1);
            }
            
            for(Book book:bookList)
            {
            	System.out.println(book.getAuthor() + ",  " + book.getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

books.json

```javascript
{
  "books": [
    {
      "category": "COOKING",
      "title": "Everyday Italian",
      "author": "Giada De Laurentiis",
      "year": "2005",
      "price": 30.00
    },
    {
      "category": "CHILDREN",
      "title": "Harry Potter",
      "author": "J K. Rowling",
      "year": "2005",
      "price": 29.99
    },
    {
      "category": "WEB",
      "title": "Learning XML",
      "author": "Erik T. Ray",
      "year": "2003",
      "price": 39.95
    }
  ]
}
```



```java
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * 采用Google GSON来处理JSON
 * @author Tom
 *
 */
public class GsonTest {
	public static void main(String[] args) {
		testJsonObject();
		System.out.println("=========华丽丽的分割线==============");
		testJsonFile();
	}
	public static void testJsonObject() {
		//构造对象
    	Person p = new Person();
    	p.setName("Tom");
    	p.setAge(20);
    	p.setScores(Arrays.asList(60,70,80));
		
    	//从Java对象到JSON字符串
		Gson gson = new Gson();
		String s = gson.toJson(p);
		System.out.println(s); //{"name":"Tom","age":20,"scores":[60,70,80]}
		
		//从JSON字符串到Java对象
		Person p2 = gson.fromJson(s, Person.class);
		System.out.println(p2.getName());  //Tom
		System.out.println(p2.getAge());   //20
		System.out.println(p2.getScores());//[60, 70, 80]
		
		//调用GSON的JsonObject
		JsonObject json = gson.toJsonTree(p).getAsJsonObject(); //将整个json解析为一颗树
		System.out.println(json.get("name"));  //"Tom"
		System.out.println(json.get("age"));   //20
		System.out.println(json.get("scores"));//[60,70,80]
		
	}
	
	public static void testJsonFile() {
		Gson gson = new Gson();
		File file = new File("books2.json");
		
        try (FileReader reader = new FileReader(file)) {
        	List<Book> books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
            
        	for(Book book : books)
        	{
        		System.out.println(book.getAuthor() + ",  " + book.getTitle());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

```



book2.json

```javascript
[
    {
      "category": "COOKING",
      "title": "Everyday Italian",
      "author": "Giada De Laurentiis",
      "year": "2005",
      "price": 30
    },
    {
      "category": "CHILDREN",
      "title": "Harry Potter",
      "author": "J K. Rowling",
      "year": "2005",
      "price": 29
    },
    {
      "category": "WEB",
      "title": "Learning XML",
      "author": "Erik T. Ray",
      "year": "2003",
      "price": 39
    }
  ]

```



```java

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;

/**
 * 采用Jackson来处理JSON
 * @author Tom
 *
 */

public class JacksonTest {

	public static void main(String[] args) throws Exception {
		testJsonObject();
		System.out.println("=========华丽丽的分割线==============");
		testJsonFile();
	}
	
	static void testJsonObject() throws IOException {
		ObjectMapper om = new ObjectMapper();
		
		//构造对象
    	Person p = new Person();
    	p.setName("Tom");
    	p.setAge(20);
    	p.setScores(Arrays.asList(60,70,80));
    	
    	//将对象解析为json字符串
		String jsonStr = om.writeValueAsString(p);
		System.out.println(jsonStr);
		
		//从json字符串重构对象
		Person p2 = om.readValue(jsonStr, Person.class);
		System.out.println(p2.getName());
		System.out.println(p2.getAge());
		System.out.println(p2.getScores());
		
		//从json字符串重构为JsonNode对象
		JsonNode node = om.readTree(jsonStr);
		System.out.println(node.get("name").asText());
		System.out.println(node.get("age").asText());
		System.out.println(node.get("scores"));		
	}
	
	static void testJsonFile() throws IOException {
		ObjectMapper om = new ObjectMapper();
		
		//从json文件中加载，并重构为java对象
		File json2 = new File("books2.json");
		List<Book> books = om.readValue(json2, new TypeReference<List<Book>>(){});
		for (Book book : books) {
			System.out.println(book.getAuthor());
			System.out.println(book.getTitle());
		}
	}	
}



```



**JSON和XML比较**  
**• 都是数据交换格式，可读性强，可扩展性高**  
**• 大部分的情况下，JSON更具优势（编码简单，转换方便），而且JSON字符长度一般小于XML，传输效率更高**  
**• XML更加注重标签和顺序**  
**• JSON会丢失信息(文件夹下，有一个：演示json会丢失信息的例子.png)**  



**总结**  
**• JSON是一种独立于编程语言的、轻量的、数据交换格式**  
**• 有多种第三方库辅助我们进行JSON生成和解析**  
**• 注意：JSON会丢失顺序性。**  

