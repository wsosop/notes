package com.wck.demo02inputstream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 1:27
 *
 * 字节输入流一次读取多个字节的方法
 * int read(byte[] b) 从输入流读取一些字节数，并将它们存储到缓冲区 b
 *  明确两件事情：
 *      1.方法参数的byte[] b 的作用？
 *          起到缓冲作用，存储每次读取到的多个字节
 *          数组的长度一般定义为1024（1kb）或者1024的整数倍
 *      2.方法的返回值 int 是什么？
 *          每次读取到的有效字符个数
 *
 * String(byte[] bytes) ：把字节数组转换为字符串
 * String(byte[] bytes, int offset, int length) ：offset转换的字节索引，length:转换的字节个数 把字节数组的一部分转化为 字符串， 通过使用平台的默认字符集解码指定的字节子阵列来构造新的 String 。
 *
 */
public class Demo02InputStream {

    public static void main(String[] args) throws IOException {
        //创建FileInputStream对象，传入输入的数据源
        //这个文件中的内容为 ABCDE
        FileInputStream fis = new FileInputStream("IO/b.txt");
        //使用FileInputStream对象中的方法 read 读取

/*        byte[] bytes=new byte[2];
        int read = fis.read(bytes);
        System.out.println(read);//2
//        System.out.println(Arrays.toString(bytes));//[65, 66]
        System.out.println(new String(bytes));//AB

        read = fis.read(bytes);
        System.out.println(read);//2
        System.out.println(new String(bytes));//CD

        read = fis.read(bytes);
        System.out.println(read);//1
        System.out.println(new String(bytes));//ED

        read = fis.read(bytes);
        System.out.println(read);//-1
        System.out.println(new String(bytes));//ED*/


        /**
         * 发现以上读取时，是一个重复的过程，可以使用循环进行优化
         * 循环优化，不知道文件中具体有多少字节，使用while 循环
         * while 结束的条件，读取到 -1结束
         */

        byte[] bytes=new byte[1024];//存储读取道的多个字节
        int len=0;//记录每次读取的有效字符个数
        while ((len=fis.read(bytes))!=-1){
            //String(byte[] bytes, int offset, int length)
            System.out.println(new String (bytes,0,len));
        }


        //关闭资源
        fis.close();

    }
}
