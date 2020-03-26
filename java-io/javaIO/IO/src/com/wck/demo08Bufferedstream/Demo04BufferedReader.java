package com.wck.demo08Bufferedstream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-26 0:33
 *
 * java.io.BufferedReader  extends java.io.Reader
 *      BufferedReader：字符缓冲输入流
 *
 *  继承自父类的共性成员方法：
 *      int read() 读取一个字符，并返回
 *      int read(char[] cbuf) 读取多个字符，将字符读入数组。
 *      abstract void close() 关闭流并释放与之相关联的任何系统资源。
 *
 *   构造方法
 *          BufferedReader(Reader in) 创建使用默认大小的输入缓冲区的缓冲字符输入流。
 *          BufferedReader(Reader in, int sz) 创建使用指定大小的输入缓冲区的缓冲字符输入流。
 *   参数：
 *          Reader in：字符输入流
 *              我们可以使用FileReader,字符输入缓冲流会给FileReader增加一个缓冲区，提高读取效率
 *          int sz：缓冲区的大小，不指定是默认值
 *
 *      特有的成员方法：
 *      String readLine() 读一行文字。读取一行数据
 *         行的终止符号（条件） ：读一行文字。 一行被视为由换行符（'\ n'），回车符（'\ r'）中的任何一个或随后的换行符终止。
 *
 *          返回值：
 *               包含行的内容的字符串，不包括任何行终止字符，如果已达到流的末尾，则为null
 *   使用步骤：
 *      1 创建一个BufferedReader对象，传递字符输入流的文件
 *      2 使用BufferedReader对象的read/readLine方法读取文本
 *      3 释放关闭资源
 */
public class Demo04BufferedReader {

    public static void main(String[] args) throws IOException {
        //1 创建一个BufferedReader对象，传递字符输入流的文件
        BufferedReader br = new BufferedReader(new FileReader("IO/i.txt"));

        //2 使用BufferedReader对象的read/readLine方法读取文本
        String str=null;
        while ((str=br.readLine()) != null){
            System.out.println(str);
        }
        //3 释放关闭资源
        br.close();
    }
}
