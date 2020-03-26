package com.wck.demo03CopyFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 15:06
 *
 * 文件复制的练习：
 *  一读一写
 *      明确：
 *          1.数据源：c:/1.jpg
 *          2.数据的目的地：d:/1.jpg
 *  文件复制的步骤：
 *      1.创建一个字节输入流对象，构造方法中传入要读取的数据源
 *      2.创建一个字节输出流对象，构造方法中传入要写入的目的地
 *      3.使用字节输入流对象的read()方法来读取文件
 *      4.使用字节输出流对象的write()方法把读取到的字节写入到目的地的文件中去
 *      5.关闭释放资源
 */
public class Demo01CopyFile {

    public static void main(String[] args) throws IOException {

        long s = System.currentTimeMillis();
        String aPath="D:\\ideaProject\\java-io\\javaIO\\ioTestDir\\a\\cat.jpeg";
        String bPath="D:\\ideaProject\\java-io\\javaIO\\ioTestDir\\b\\cat.jpeg";

        // 1.创建一个字节输入流对象，构造方法中传入要读取的数据源
        FileInputStream fis = new FileInputStream(aPath);
        // 2.创建一个字节输出流对象，构造方法中传入要写入的目的地
        FileOutputStream fos = new FileOutputStream(bPath);
        //3.使用字节输入流对象的read()方法来读取文件
/*        int len=0;
        while ((len=fis.read())!=-1){
            //4.使用字节输出流对象的write()方法把读取到的字节写入到目的地的文件中去
            fos.write(len);//818毫秒
        }*/

        // 优化，使用数组缓冲读取多个字节，写入多个字节
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=fis.read(bytes))!=-1){
            fos.write(bytes,0,len);//5毫秒
        }
        //5.关闭释放资源
        fos.close();
        fis.close();

        long e = System.currentTimeMillis();
        System.out.println("复制所有时间为："+(e-s)+"毫秒");

    }
}
