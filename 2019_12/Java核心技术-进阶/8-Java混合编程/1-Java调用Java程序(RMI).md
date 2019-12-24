## Java调用Java程序(RMI)

**Java 混合编程**  
**• 众多编程语言，各有各的特点和应用范围**  
**– https://www.tiobe.com/tiobe-index/**  
**• 现实世界存在很多应用程序**  
**– 由不同编程语言实现**  
**– 分布式部署的**  
**• Java混合编程**  
**– Java程序和其他应用程序进行通讯和数据交互**  
**– Java和Java/C/JS/Python/Web Service/命令行的混合编程**  



**RMI(1)**  
**• 回顾下学习Java程序过程**  
**– 在main函数里面完成所有功能**  
**– 基于函数/方法将功能拆开，采用函数相互调用**  
**– 类对象/继承/多态**  
**– A a = new A(); a.f1(); //完成某一个功能**  



**RMI(2)**  
**• 单虚拟机JVM上的程序运行**  
**– 启动一个main程序，然后重复以下的2个步骤**  
**• new 出一个对象**  
**• 调用对象的某一个方法**  
**• 多虚拟机JVM的程序运行**  
**– 启动多个main程序，这些程序可以部署在多个机器/虚拟机上**  
**– 多个进程可通过网络互相传递消息进行协作**  
**– 进程通过RMI可调用另一个机器的Java的函数**  



**RMI(3)**  
**• RMI：Remote Method Invocation 远程方法调用**  
**– 两个位于不同JVM虚拟机的Java程序互相请求访问**  

<img src=".\RMI示意图.png" alt="RMI示意图" style="zoom:67%;" />



**RMI(5)**  
**• RMI的参数和返回值**  
**– (自动化)传递远程对象(实现Remote接口)**  
**• 当一个对远程对象的引用从一个JVM传递到另一个JVM，该远程对象的发送者和接收者将持有同一个实体对象的引用。这个引用并非是一个内存位置，而是由网络地址和该远程对象的唯一标识符构成的。<u>###两个JVM拥有同一个对象###</u>**  
**– (自动化)传递可序列化对象(实现Serializable接口)**  
**• JVM中的一个对象经过序列化后的字节，通过网络，其副本传递到另一个JVM中，并重新还原为一个Java对象。<u>###每个JVM拥有自己的对象###</u>**  

```java

目录分为客户端(MOOC20-01Client)和服务端(MOOC20-01Client)

├─MOOC20-01Client
│  │  client.policy
│  │          
│  └─src
│      ├─warehouse1
│      │      Warehouse.java
│      │      WarehouseClient.java
│      │      
│      └─warehouse2
│              Book.java
│              Product.java
│              Warehouse.java
│              WarehouseClient.java
│              
└─MOOC20-01Server
    │  server.policy         
    └─src
        ├─warehouse1
        │      Warehouse.java
        │      WarehouseImpl.java
        │      WarehouseServer.java
        │      
        └─warehouse2
                Book.java
                Product.java
                Warehouse.java
                WarehouseImpl.java
                WarehouseServer.java
                
```



客户端：

```java
package warehouse1;
import java.rmi.*;

/**
 * 接口类
 * 
 */
public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}

```

```java
package warehouse1;
import java.rmi.*;
import java.util.*;
import javax.naming.*;


public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      Context namingContext = new InitialContext();
      
      //开始查找RMI注册表上有哪些绑定的服务
      System.out.print("RMI 注册表绑定列表: ");
      Enumeration<NameClassPair> e = namingContext.list("rmi://127.0.0.1:8001/");
      while (e.hasMoreElements())
         System.out.println(e.nextElement().getName());
      
      //获取某一个地址上的服务类
      String url = "rmi://127.0.0.1:8001/warehouse1";      
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      //System.out.println(centralWarehouse.getClass().getName());
      
      //输入参数  取得结果
      String descr = "面包机";
      double price = centralWarehouse.getPrice(descr);
      System.out.println(descr + ": " + price);
   }
}

```

```java
package warehouse2;

public class Book extends Product
{
   private String isbn;

   public Book(String title, String isbn, double price)
   {
      super(title, price);
      this.isbn = isbn;
   }
   
   public String getDescription()
   {
      return super.getDescription() + " " + isbn;
   }  
}

```

```java
package warehouse2;

import java.io.*;

/**
 * 可序列化对象，实现Serializable接口，对象可以序列化为字节，在多个JVM中传递。
 * 客户端和服务端的JVM里面，都有对象的拷贝。
 * 远程对象，实现Remote接口，对象不可以实例化。
 * 对象只存在服务端中，客户端拥有指向这样远程对象的指针，但客户端JVM里面没有对象的值得拷贝
 * @author YuXiangKaoChi
 *
 */
public class Product implements Serializable
{
   private String description;
   private double price;
   private Warehouse location;

   public Product(String description, double price)
   {
      this.description = description;
      this.price = price;
   }

   public String getDescription()
   {
      return description;
   }

   public double getPrice()
   {
      return price;
   }

   public Warehouse getLocation()
   {
      return location;
   }

   public void setLocation(Warehouse location)
   {
      this.location = location;
   }
}

```

```java
package warehouse2;
import java.rmi.*;
import java.util.*;


/**
 * 可序列化对象，实现Serializable接口，对象可以序列化为字节，在多个JVM中传递。
 * 客户端和服务端的JVM里面，都有对象的拷贝。
 * 远程对象，实现Remote接口，对象不可以实例化。
 * 对象只存在服务端中，客户端拥有指向这样远程对象的指针，但客户端JVM里面没有对象的值得拷贝
 * @author YuXiangKaoChi
 *
 */
public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
   Product getProduct(List<String> keywords) throws RemoteException;
}

```

```java
package warehouse2;

import java.rmi.*;
import java.util.*;
import javax.naming.*;


/**
 * 可序列化对象，实现Serializable接口，对象可以序列化为字节，在多个JVM中传递。
 * 客户端和服务端的JVM里面，都有对象的拷贝。
 * 远程对象，实现Remote接口，对象不可以实例化。
 * 对象只存在服务端中，客户端拥有指向这样远程对象的指针，但客户端JVM里面没有对象的值得拷贝
 * @author YuXiangKaoChi
 *
 */

public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
	   //设置 java 安全策略
      System.setProperty("java.security.policy", "client.policy");
      System.setSecurityManager(new SecurityManager());
      Context namingContext = new InitialContext();
      
      //开始查找RMI注册表上有哪些绑定的服务
      System.out.print("RMI 注册表绑定列表: ");
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost:8001/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      //获取某一个地址上的服务类
      String url = "rmi://localhost:8001/warehouse2";      
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      
      Scanner in = new Scanner(System.in);
      System.out.print("Enter keywords: ");
      List<String> keywords = Arrays.asList(in.nextLine().split("\\s+"));
      Product prod = centralWarehouse.getProduct(keywords);
      
      System.out.println(prod.getDescription() + ": " + prod.getPrice());
      System.out.println(prod.getLocation());
   }
}

```



client.policy

```java
grant 
{  
   permission java.security.AllPermission;
};

```



MOOC20-01Server:

```java
package warehouse1;
import java.rmi.*;


public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}

```

```java
package warehouse1;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class WarehouseImpl extends UnicastRemoteObject implements Warehouse
{
   private Map<String, Double> prices;

   public WarehouseImpl() throws RemoteException
   {
	  //物品列表
      prices = new HashMap<>();
      prices.put("面包机", 24.95);
      prices.put("微波炉", 49.95);
   }

   public double getPrice(String description) throws RemoteException
   {
      Double price = prices.get(description);
      return price == null ? 0 : price;
   }
}

```

```java
package warehouse1;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import javax.naming.*;

/**
 * 产生WarehouseImpl对象，并进行注册在8001端口，对外提供服务
 * 
 */
public class WarehouseServer
{
   public static void main(String[] args) throws Exception
   {
      System.out.println("产生服务器对象");
      WarehouseImpl centralWarehouse = new WarehouseImpl();

      System.out.println("将服务器对象绑定在8001端口，对外提供服务");
      LocateRegistry.createRegistry(8001);//定义端口号
      Naming.rebind("rmi://127.0.0.1:8001/warehouse1", centralWarehouse);

      System.out.println("等待客户端的调用");
   }
}

```

```java
package warehouse2;

public class Book extends Product
{
   private String isbn;

   public Book(String title, String isbn, double price)
   {
      super(title, price);
      this.isbn = isbn;
   }
   
   public String getDescription()
   {
      return super.getDescription() + " " + isbn;
   }  
}

```

```java
package warehouse2;

import java.io.*;

public class Product implements Serializable
{
   private String description;
   private double price;
   private Warehouse location;

   public Product(String description, double price)
   {
      this.description = description;
      this.price = price;
   }

   public String getDescription()
   {
      return description;
   }

   public double getPrice()
   {
      return price;
   }

   public Warehouse getLocation()
   {
      return location;
   }

   public void setLocation(Warehouse location)
   {
      this.location = location;
   }
}

```

```java
package warehouse2;

import java.rmi.*;
import java.util.*;


public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
   Product getProduct(List<String> keywords) throws RemoteException;
}

```

```java
package warehouse2;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class WarehouseImpl extends UnicastRemoteObject implements Warehouse
{
   private Map<String, Product> products;
   private Warehouse backup;

   /**
    * Constructs a warehouse implementation.
    */
   public WarehouseImpl(Warehouse backup) throws RemoteException
   {
      products = new HashMap<>();
      this.backup = backup;
   }

   public void add(String keyword, Product product)
   {
      product.setLocation(this);      
      products.put(keyword, product);
   }
   
   public double getPrice(String description) throws RemoteException
   {
      for (Product p : products.values())
         if (p.getDescription().equals(description)) return p.getPrice();
      if (backup == null) return 0;
      else return backup.getPrice(description);
   }
   
   public Product getProduct(List<String> keywords) throws RemoteException
   {
      for (String keyword : keywords)
      {
         Product p = products.get(keyword);
         if (p != null) return p;
      }
      if (backup != null)
         return backup.getProduct(keywords);
      else if (products.values().size() > 0)
         return products.values().iterator().next();
      else
         return null;
   }
}

```

```java
package warehouse2;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import javax.naming.*;

public class WarehouseServer
{
   public static void main(String[] args) throws Exception
   {
      System.setProperty("java.security.policy", "server.policy");
      System.setSecurityManager(new SecurityManager());
      
      System.out.println("Constructing server implementation...");
      WarehouseImpl backupWarehouse = new WarehouseImpl(null);
      WarehouseImpl centralWarehouse = new WarehouseImpl(backupWarehouse);
      
      centralWarehouse.add("toaster", new Product("Blackwell Toaster", 23.95));
      backupWarehouse.add("java", new Book("Core Java vol. 2", "0132354799", 44.95));

      System.out.println("Binding server implementation to registry...");
//      Context namingContext = new InitialContext();
//      namingContext.bind("rmi:central_warehouse", centralWarehouse);
      LocateRegistry.createRegistry(8001);//定义端口号
      Naming.rebind("rmi://127.0.0.1:8001/warehouse2", centralWarehouse);

      System.out.println("Waiting for invocations from clients...");
   }
}

```

server.policy

```java
grant 
{  
   permission java.security.AllPermission;
};

```

本目录下有 代码zip包



**RMI(6)**  
**• RMI优点**  
**– 跨平台分布式对象调用**  
**– 完全对象支持**  
**– 安全策略**  
**• RMI缺点**  
**– 双方必须是Java语言实现**  
**– 不如消息传递协作方便**  



**RMI(7)**  
**• 总结**  
**– 理解Java混合编程概念**  
**– 了解分布式对象调用概念**  
**– 了解Java RMI的实现**  