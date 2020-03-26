package com.wck.demo08Bufferedstream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-26 0:16
 *
 * java.io.BufferedWriter extends java.io.Writer
 *  BufferedWriter:字符输出缓冲流
 *  继承自父类的共性成员方法：
 *    void write(int c) 写入单个字符。
 *    void write(char[] cbuf) 写入字符数组。
 *    abstract void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
 *    void write(String str) 写入字符串。
 *    void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
 *    void flush() 刷新该流的缓冲。
 *    void close() 关闭此流，但要先刷新它。
 *
 *    构造方法：
 *          BufferedWriter(Writer out) 创建使用默认大小的输出缓冲区的缓冲字符输出流。
 *          BufferedWriter(Writer out, int sz) 创建一个新的缓冲字符输出流，使用给定大小的输出缓冲区。
 *     参数：
 *      Writer out：字符输出流
 *             我们传递FileWriter,缓冲流会给FileWriter增加一个缓冲区，提高FileWriter的写入效率
 *      int sz：缓冲区的大小，默认是1024
 *
 *      特有的成员方法：
 *          void newLine() 写一行行分隔符。好处：会根据不同的操作系统，获取对应的行分割符
 *
 *      换行：换行符号
 *          Windows：\r\n
 *          linux:/n
 *          mac:/r
 *
 *    使用步骤：
 *          1 创建一个缓冲字符输出流对象，传递字符输出流
 *          2 调用缓冲字符输出流对象的writer方法，把数据写入到内存缓冲区中
 *          3 调用字符缓冲输出流的flush方法，把内存中的数据刷新到文件中去
 *          4 关闭释放资源
 */
public class Demo03BufferedWriter {

    public static void main(String[] args) throws IOException {
        //1 创建一个缓冲字符输出流对象，传递字符输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter("IO/i.txt"));
        //2 调用缓冲字符输出流对象的writer方法，把数据写入到内存缓冲区中

        for (int i = 0; i < 10; i++) {
            bw.write("你好世界，夜深了"+i);
            bw.newLine();
        }

        //3 调用字符缓冲输出流的flush方法，把内存中的数据刷新到文件中去
        bw.flush();
        //4 关闭释放资源
        bw.close();
    }
}
