package com.wck.demo08Bufferedstream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 23:15
 *
 * java.io.BufferedOutputStream  extends java.io.OutputStream
 *
 *  BufferedOutputStream:字节输出缓冲流
 *  继承自父类的共性成员方法：
 *    - public void close() ：关闭此输出流并释放与此流相关联的任何系统资源。
 *    - public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
 *    - public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
 *    - public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
 *    - public abstract void write(int b) ：将指定的字节输出流。
 *
 *  构造方法：
 *      BufferedOutputStream(OutputStream out) 创建一个新的缓冲输出流，以将数据写入指定的底层输出流。
 *      BufferedOutputStream(OutputStream out, int size) 创建一个新的缓冲输出流，以便以指定的缓冲区大小将数据写入指定的底层输出流。
 *
 *      参数：
 *          OutputStream out:字节输出流
 *              我们可以传递一个FileOutputStream,缓冲流会给FileOutputStream增加一个缓冲区，提高FileOutputStream的写入效率
 *          int size ：指定缓冲流内部缓冲区的大小，不指定默认为1024
 *      使用步骤（重点）：
 *          1.创建一个FileOutputStream对象，传入一个所要写入数据的目的地
 *          2.创建BufferedOutputStream对象，构造方法中传递FileOutputStream对象,提高FileOutputStream对象的效率
 *          3.BufferedOutputStream对象中的方法writer,把数据写入到内部缓冲区中
 *          4.使用BufferedOutputStream对象中的flush方法，把内部缓冲区的数据刷新到文件中
 *          5.释放关闭资源（释放资源会先调用flush刷新数据，第4步可以省略）
 *
 *
 */
public class Demo01BufferedOutputStream {

    public static void main(String[] args) throws IOException {

        //1 创建一个FileOutputStream对象，传入一个所要写入数据的目的地
        FileOutputStream fos = new FileOutputStream("IO/h.txt");
        //2 创建BufferedOutputStream对象，构造方法中传递FileOutputStream对象
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //3 BufferedOutputStream对象中的方法writer,把数据写入到内部缓冲区中
        bos.write("把数据写入到内部的缓冲区中".getBytes());
        //4 使用BufferedOutputStream对象中的flush方法，把内部缓冲区的数据刷新到文件中
        bos.flush();
        //5 释放关闭资源（释放资源会先调用flush刷新数据，第4步可以省略）
        bos.close();





    }
}
