package com.wck.demo08Bufferedstream.copyfile;

import java.io.*;

/**
 * @author 御香烤翅
 * @create 2020-03-26 0:05
 *  文件复制的练习：
 *   一读一写
 *       明确：
 *           1.数据源：c:/1.jpg
 *           2.数据的目的地：d:/1.jpg
 *   文件复制的步骤：
 *   1 创建字节缓冲输入流对象，构造方法中传递字节输入流
 *   2 创建字节缓冲输出流对象，构造方法中传递字节输出流
 *   3 使用字节缓冲输入流兑现的read方法。读取文件
 *   4 使用字节缓冲输出流对象，把读取的 数据写入到内部缓冲区中
 *   5 释放资源（会把缓冲区中的数据刷新到文件中）
 *
 *
 */
public class Demo02CopyFile {

    public static void main(String[] args) throws IOException {
        long s = System.currentTimeMillis();
        String aPath="D:\\ideaProject\\java-io\\javaIO\\ioTestDir\\a\\cat.jpeg";
        String bPath="D:\\ideaProject\\java-io\\javaIO\\ioTestDir\\b\\cat.jpeg";

        // 1.创建字节缓冲输入流对象，构造方法中传递字节输入流
        FileInputStream fis = new FileInputStream(aPath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        // 2.创建字节缓冲输出流对象，构造方法中传递字节输出流
        FileOutputStream fos = new FileOutputStream(bPath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //3 使用字节缓冲输入流兑现的read方法。读取文件

        int len=0;
        byte[] bytes=new byte[1024];
        while ((len=bis.read(bytes))!=-1){
            //4 使用字节缓冲输出流对象，把读取的 数据写入到内部缓冲区中
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();
        long e = System.currentTimeMillis();
        System.out.println("复制所有时间为："+(e-s)+"毫秒");
    }
}
