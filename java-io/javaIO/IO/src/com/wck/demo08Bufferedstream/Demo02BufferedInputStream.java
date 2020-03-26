package com.wck.demo08Bufferedstream;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 23:39
 *
 * java.io.BufferedInputStream extends java.io.InputStream
 *    BufferedInputStream:字节缓冲输入流
 *
 *  继承自父类的共性方法：
 *      abstract int read() 从输入流读取数据的下一个字节。
 *      int read(byte[] b) 从输入流读取一些字节数，并将它们存储到缓冲区 b 。
 *      void close() 关闭此输入流并释放与流相关联的任何系统资源
 *
 *  构造方法：
 *      BufferedInputStream(InputStream in) 创建一个 BufferedInputStream并保存其参数，输入流 in ，供以后使用。
 *      BufferedInputStream(InputStream in, int size)  创建 BufferedInputStream具有指定缓冲区大小，并保存其参数，输入流 in ，供以后使用。
 *  参数：
 *      InputStream in :字节输入流
 *          我们可以传递FileInputStream,缓冲流会给FileInputStream增加一个缓冲区，提高FileInputStream的读取效率
 *      int size ：指定缓冲流内部缓冲区的大小，不指定默认为1024
 *  步骤：
 *      1 创建一个FileInputStream对象，传入要绑定读取数据的文件
 *      2 创建BufferedInputStream对象，构造方法中传递FileInputStream对象，提高FileInputStream对象的读取效率
 *      3 使用BufferedInputStream对象的read方法，读取文件
 *      4 释放资源
 */
public class Demo02BufferedInputStream {

    public static void main(String[] args) throws IOException {

        //1 创建一个FileInputStream对象，传入要绑定读取数据的文件
        FileInputStream fis = new FileInputStream("IO/h.txt");
        //2 创建BufferedInputStream对象，构造方法中传递FileInputStream对象
        BufferedInputStream bis = new BufferedInputStream(fis);
        //3 使用BufferedInputStream对象的read方法，读取文件

       /* //int read() 从输入流读取数据的下一个字节。
        int len=0;
        while ((len=bis.read())!=-1){
            System.out.println(len);
        }*/

       //int read(byte[] b)
        byte[] bytes=new byte[1024];

        int len=0;
        while ((len=bis.read(bytes)) != -1){

            System.out.println(new String(bytes,0,len));

        }

        //4 释放资源
        bis.close();

    }
}
