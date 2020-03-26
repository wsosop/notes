package com.wck.demo05writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 16:56
 *
 *  flush和close方法的区别
 *  void flush() 刷新流 ->刷新缓冲区，流对象可以继续使用
 *  void close() 关闭流，先刷新->先刷新缓冲区，然后通知系统释放资源，流对象不可以再被使用了
 *
 *
 */
public class Demo02WriterCloseAndFlush {

    public static void main(String[] args) throws IOException {

        //1.创建一个FileWriter对象传入要写入数据的目的地
        FileWriter fw = new FileWriter("IO/d.txt");
        //2.使用FileWriter里面的write方法，把数据写入到内存的缓冲区中（字符转换为字节的过程）
        //void write(int c) 写入单个字符。
        fw.write(97);
        //3.使用FileWriter里面的flush方法，把内存缓冲区的数据，刷新到文件中
        fw.flush();
        //刷新之后，流可以继续使用
        fw.write(98);
        //4.释放关闭资源（会先把内存缓冲区的数据刷新到文件中）
        fw.close();
        //close方法之后，流已经关闭了，已经从内存中消失了，流就不能在使用了
        //fw.write(99); 这句写法错误

    }

}
