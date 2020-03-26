package com.wck.demo01outputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 御香烤翅
 * @create 2020-03-25 0:02
 *
 * 一次写多个字节
 * - public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
 * - public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
 *
 */
public class Demo02OutputStream {

    public static void main(String[] args) throws IOException {

        //创建FileOutputStream对象，传入将要写的数据的目的地
        FileOutputStream fos=new FileOutputStream(new File("IO/b.txt"));
        //调用FileOutputStream对象的write方法，把数据写入到文件中
        //在文件显示100，写几个自己
        fos.write(49);
        fos.write(48);
        fos.write(48);

        /**
         * public void write(byte[] b)
         *  一次写入多个字节
         */
        byte[] bytes={65,66,67,68,69};
        fos.write(bytes);

        /**
         * 写字节数组的一部分
         * public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
         * 参数
         *      int off ：数组的开始索引
         *      int len : 写几个字节
         */
        fos.write(bytes,1,2);

        /**
         * 写入字符串的方法：
         *  String 类中的getBytes()方法，把字符串转换为 byte[] 字节数组
         */
        byte[] bytes1="你好".getBytes();
        System.out.println(bytes1.length);
        System.out.println(Arrays.toString(bytes1));//[-28, -67, -96, -27, -91, -67]
        fos.write(bytes1);

        //关闭流
        fos.close();

    }
}
