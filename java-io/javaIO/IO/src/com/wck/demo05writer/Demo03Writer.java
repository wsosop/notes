package com.wck.demo05writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 19:24
 *
 * 字符输出流写数据的其他方法
 *    void write(char[] cbuf) 写入字符数组。
 *    abstract void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
 *    void write(String str) 写入字符串。
 *    void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
 */
public class Demo03Writer {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("IO/e.txt");

        char[] cbuf={'a','b','c','d','e'};
        //write(char[] cbuf) 写入字符数组。
        fw.write(cbuf);
        //void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,
        fw.write(cbuf,0,2);
        //write(String str) 写入字符串。
        fw.write("你好世界");
        //write(String str, int off, int len)
        fw.write("我是程序员",2,3);
        fw.close();
    }

}
