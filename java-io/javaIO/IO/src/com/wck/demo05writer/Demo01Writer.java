package com.wck.demo05writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 16:56
 *
 * java.io.Writer :字符输出流，是所有字符输出流的最顶层的类，是一个抽象类
 * public abstract class Writer
 *
 *  共性的成员方法：
 *    void write(int c) 写入单个字符。
 *    void write(char[] cbuf) 写入字符数组。
 *    abstract void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
 *    void write(String str) 写入字符串。
 *    void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
 *    void flush() 刷新该流的缓冲。
 *    void close() 关闭此流，但要先刷新它。
 *
 *  FileWriter extends OutputStreamWriter ->Writer
 *      FileWriter:文件字符输出流
 *          作用：把内存中的字符数据写入到文件中
 *      构造方法：
 *      FileWriter(File file) 给一个File对象构造一个FileWriter对象。
 *      FileWriter(String fileName) 构造一个给定文件名的FileWriter对象。
 *          参数：写入数据的目的地
 *              File file : 写入的文件
 *              String fileName ： 写入的文件路径
 *
 *      构造方法的作用：
 *          1.创建一个FileWriter对象
 *          2.会根据都早方法中传递的文件、路径创建一个文件
 *          3.把创建的FileWriter对象指向创建好的这个文件
 *
 *  字符输出流的使用步骤（重点）
 *      1.创建一个FileWriter对象传入要写入数据的目的地
 *      2.使用FileWriter里面的write方法，把数据写入到内存的缓冲区中（字符转换为字节的过程）
 *      3.使用FileWriter里面的flush方法，把内存缓冲区的数据，刷新到文件中
 *      4.释放关闭资源（会先把内存缓冲区的数据刷新到文件中）
 *
 */
public class Demo01Writer {

    public static void main(String[] args) throws IOException {

        //1.创建一个FileWriter对象传入要写入数据的目的地
        FileWriter fw = new FileWriter("IO/d.txt");
        //2.使用FileWriter里面的write方法，把数据写入到内存的缓冲区中（字符转换为字节的过程）
        //void write(int c) 写入单个字符。
        fw.write(98);
        //3.使用FileWriter里面的flush方法，把内存缓冲区的数据，刷新到文件中
//        fw.flush();
        //4.释放关闭资源（会先把内存缓冲区的数据刷新到文件中）
        fw.close();
    }

}
