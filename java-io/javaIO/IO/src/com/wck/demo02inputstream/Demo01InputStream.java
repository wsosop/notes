package com.wck.demo02inputstream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 0:47
 * java.io.InputStream :字节输出流
 *  这个抽象类是表示输入字节流的所有类的超类。
 * public abstract class InputStream
 *
 *  定义了所有子类共性的方法：
 *      abstract int read() 从输入流读取数据的下一个字节。
 *      int read(byte[] b) 从输入流读取一些字节数，并将它们存储到缓冲区 b 。
 *      void close() 关闭此输入流并释放与流相关联的任何系统资源。
 *
 * java.io.FileInputStream extends InputStream
 *  FileInputStream:文件字节输入流
 *  作用：把文件里的数据字节流读取到内存中使用
 *
 *  构造方法：
 *      FileInputStream(String name) 通过打开与实际文件的连接来创建一个 FileInputStream ，该文件由文件系统中的路径名 name命名。
 *      FileInputStream(File file) 通过打开与实际文件的连接创建一个 FileInputStream ，该文件由文件系统中的 File对象 file命名。
 *
 *      参数： 读取文件的数据源
 *          String name ： 文件的路径
 *          File file ： 文件
 *
 *   构造方法的作用：
 *      1.创建FileInputStream对象
 *      2.会把FileInputStream对象指向构造方法中要读取的文件
 *
 *  读取数据的原理（硬盘->内存）
 *
 *  java程序->JVM->OS->OS读取数据的方法->读取文件
 *
 *  字节输入流的使用步骤：
 *      1.创建FileInputStream对象,构造方法中绑定要读取的数据源
 *      2.使用FileInputStream对象的read读取文件
 *      3.释放资源
 */
public class Demo01InputStream {

    public static void main(String[] args) throws IOException {
        //1.创建FileInputStream对象,构造方法中绑定要读取的数据源
        //a.txt 里面的内容就只有 abc 三个字母
        FileInputStream fis = new FileInputStream("IO/a.txt");
        //2.使用FileInputStream对象的read读取文件
        //int read() 读取文件中的一个字节并返回，读取到文件的末尾会返回-1
  /*      int len = fis.read();
        System.out.println(len);//97
        len = fis.read();
        System.out.println(len);//98
        len = fis.read();
        System.out.println(len);//99

        len = fis.read();
        System.out.println(len);//-1

        len = fis.read();
        System.out.println(len);//-1*/

        //使用while循环优化
        int len=0;

        while ((len=fis.read()) != -1){
            System.out.println((char) len);
        }
        //3.释放资源
        fis.close();
    }
}
