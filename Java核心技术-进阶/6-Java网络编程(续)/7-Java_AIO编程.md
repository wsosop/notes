## Java AIO 编程

**BIO**  
**• 传统的Java TCP通讯：Blocking I/O ** 



**NIO**  
**• Non-Blocking I/O**  
**– 提供非阻塞等方式**  
**– 避免I/O阻塞通讯效率过低**  
**– 一个线程可以管理多个连接**  
**– 减少线程多的压力**  
**– <u>不是真异步</u>,是一个同步的模式**  



**假设背景，是你去饭店点个单吃饭。**  
**同步阻塞**：<u>你下完单，等在饭店里面，啥事也不能做，等待厨师制作完成，并亲自和饭店完成交接。</u>   
**同步非阻塞**：<u>你下完单，可以外出，不用一直等待。但是会采用定期轮询的办法，随时来看饭菜是否完成。如果已制作完成，你亲自和饭店完成交接。</u>   
**异步非阻塞**：<u>你下完单，可以外出，也不用定期轮询。而是交代下来，制作完成后，自动送到家里。（即制作完成后，自动进行一个回调函数执行（自动送达操作）。）</u>  



<u>**并发编程的同步**：是指多个线程需要以一种同步的方式，来访问某一个数据结构。这里的同步反义词是非同步的,即线程不安全的。</u>  
<u>**网络通讯的同步**：是指客户端和服务端直接的通讯等待方式。这里的同步的反义词是异步,即无需等待另外一端操作完成。</u>  



**AIO**  
**• Asynchronous I/O, 异步I/O**  
**• JDK 1.7引入，主要在java.nio包中**  
**• 异步I/O，采用回调方法进行处理读写操作**  
**• 主要类**  
**– AsynchronousServerSocketChannel 服务器接受请求通道**  
**• bind 绑定在某一个端口 accept 接受客户端请求**  
**– AsynchronousSocketChannel Socket通讯通道**  
**• read 读数据 write 写数据**  
**– CompletionHandler 异步处理类**  
**• completed 操作完成后异步调用方法 failed 操作失败后异步调用方法**  

```java
package aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AioServer {

    public static void main(String[] args) throws IOException {  
    	AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();   
        server.bind(new InetSocketAddress("localhost", 8001));  
        System.out.println("服务器在8001端口守候");
        
        //开始等待客户端连接，一旦有连接，做26行任务
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {  
            @Override  
            public void completed(AsynchronousSocketChannel channel, Object attachment) {  
            	 server.accept(null, this); //持续接收新的客户端请求
            	 
                 ByteBuffer buffer = ByteBuffer.allocate(1024); //准备读取空间
                 //开始读取客户端内容，一旦读取结束，做33行任务
                 channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                     @Override
                     public void completed(Integer result_num, ByteBuffer attachment) {
                         attachment.flip(); //反转此Buffer 
                         CharBuffer charBuffer = CharBuffer.allocate(1024);
                         CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                         decoder.decode(attachment,charBuffer,false);
                         charBuffer.flip();
                         String data = new String(charBuffer.array(),0, charBuffer.limit());
                         System.out.println("client said: " + data);
                         channel.write(ByteBuffer.wrap((data + " 666").getBytes())); //返回结果给客户端
                         try{
                             channel.close();
                         }catch (Exception e){
                        	 e.printStackTrace();
                         }
                     }
      
                     @Override
                     public void failed(Throwable exc, ByteBuffer attachment) {
                         System.out.println("read error "+exc.getMessage());
                     }
                 });
                 

            }  
  
            @Override  
            public void failed(Throwable exc, Object attachment) {  
                System.out.print("failed: " + exc.getMessage());  
            }  
        });  

        while(true){
        	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }  
}

```

```java
package aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.UUID;


public class AioClient {

	public static void main(String[] a) {
		try
		{
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			
			//18行连接成功后，自动做20行任务
			channel.connect(new InetSocketAddress("localhost", 8001), null, new CompletionHandler<Void, Void>() {

				public void completed(Void result, Void attachment) {
					String str = UUID.randomUUID().toString();
					
					//24行向服务器写数据成功后，自动做28行任务
					channel.write(ByteBuffer.wrap(str.getBytes()), null,
							new CompletionHandler<Integer, Object>() {

								@Override
								public void completed(Integer result, Object attachment) {
									try {
										System.out.println("write " + str + ", and wait response");
										//等待服务器响应
										ByteBuffer buffer = ByteBuffer.allocate(1024); //准备读取空间
						                 //开始读取服务器反馈内容，一旦读取结束，做39行任务
										channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
						                     @Override
						                     public void completed(Integer result_num, ByteBuffer attachment) {
						                         attachment.flip(); //反转此Buffer 
						                         CharBuffer charBuffer = CharBuffer.allocate(1024);
						                         CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
						                         decoder.decode(attachment,charBuffer,false);
						                         charBuffer.flip();
						                         String data = new String(charBuffer.array(),0, charBuffer.limit());
						                         System.out.println("server said: " + data);
						                         try{
						                             channel.close();
						                         }catch (Exception e){
						                        	 e.printStackTrace();
						                         }
						                     }
						      
						                     @Override
						                     public void failed(Throwable exc, ByteBuffer attachment) {
						                         System.out.println("read error "+exc.getMessage());
						                     }
						                 });
						                 
										channel.close();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

								@Override
								public void failed(Throwable exc, Object attachment) {
									System.out.println("write error");
								}

							});
				}

				public void failed(Throwable exc, Void attachment) {
					System.out.println("fail");
				}

			});
			Thread.sleep(10000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```



**三种I/O的区别**

|                       | BIO  | NIO    | AIO    |
| --------------------- | ---- | ------ | ------ |
| 阻塞方式              | 阻塞 | 非阻塞 | 非阻塞 |
| 同步方式              | 同步 | 同步   | 异步   |
| 编程难度              | 简单 | 较难   | 困难   |
| 客户机/服务器线程对比 | 1    | N:1    | N:1    |
| 性能                  | 低   | 高     | 高     |

**总结**  
**• 了解BIO/NIO/AIO的区别**  
**• 掌握AIO回调编程**



