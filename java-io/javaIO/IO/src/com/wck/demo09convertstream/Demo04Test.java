package com.wck.demo09convertstream;

import java.io.*;

/**
 * @author 御香烤翅
 * @create 2020-03-26 16:39
 *
 * 将GBK编码的文本文件，转换为UTF-8编码的文本文件。
 * 分析步骤：
 *
 *      1.创建 InputstreamReader对象构造方法中传字节输入流和指定的编码表名称GBK
 *      2.创建Outputstreomiriter对象构造方法中传递字节输出流和指定的编码表名称UTF-8
 *      3.使用 InputstreamReader对象中的方法read读取文件
 *      4.使用 outputstreamiriterwrit对象中的方法把读取的数据写入到文件中
 *      5.释放资源
 */
public class Demo04Test {

    public static void main(String[] args) throws IOException {
//        base();//字符数组8毫秒，非字符数组 50毫秒

        buffer();//8毫秒

    }

    private static void buffer() throws IOException {

        long s = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("IO/gbk.txt"), "gbk"));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("IO/utf-8-2.txt"), "utf-8"));

        char[] chars=new char[1024];
        int len=0;
        while ((len=br.read(chars))!=-1){
            bw.write(chars,0,len);
        }
        bw.close();
        br.close();
        long e = System.currentTimeMillis();
        System.out.println("复制所有时间为："+(e-s)+"毫秒");

    }

    private static void base() throws IOException {
        long s = System.currentTimeMillis();
        //1.创建 InputstreamReader对象构造方法中传字节输入流和指定的编码表名称GBK
        InputStreamReader isr = new InputStreamReader(new FileInputStream("IO/gbk.txt"), "gbk");
        //2.创建Outputstreomiriter对象构造方法中传递字节输出流和指定的编码表名称UTF-8
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("IO/utf-8-2.txt"),"utf-8");
    /*   int len=0;
        // 3.使用 InputstreamReader对象中的方法read读取文件
        while ((len=isr.read())!=-1){
            //4.使用 outputstreamiriterwrit对象中的方法把读取的数据写入到文件中
            osw.write(len);
        }*/

        //使用数组
        char[] chars=new char[1024];
        int len=0;
        while ((len=isr.read(chars))!=-1){
            osw.write(chars,0,len);
        }
        //5.释放资源
        osw.close();
        isr.close();
        long e = System.currentTimeMillis();
        System.out.println("复制所有时间为："+(e-s)+"毫秒");
    }

}
