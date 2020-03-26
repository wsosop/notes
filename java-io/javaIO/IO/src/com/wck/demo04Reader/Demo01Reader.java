package com.wck.demo04Reader;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 16:09
 *
 * java.io.Reader
 * public abstract class Reader
 * 字符输出流，是字符输入流的最顶层的父类，定义了一些共性的成员方法，是一个抽象类
 *  共性的成员方法：
 *      int read() 读取一个字符，并返回
 *      int read(char[] cbuf) 读取多个字符，将字符读入数组。
 *      abstract void close() 关闭流并释放与之相关联的任何系统资源。
 *
 * java.io.FileReader extends InputStreamReader ->extends Reader
 *      FileReader:文件字符输入流
 *      作用：把硬盘文件中的数据以字符的方式读取到内存中
 *
 *  构造函数：
 *      FileReader(File file)
 *      FileReader(String fileName)
 *          参数：要读取的数据源
 *              File file : 一个文件
 *              String fileName : 文件的路径
 *  FileReader 构造方法的作用：
 *      1.创建一个FileReader对象
 *      2.会把创建的FileReader对象指向要读取的文件
 *
 *
 * FileReader字符输入流的使用步骤：
 *  1.创建FileReader对象，构造方法中绑定要读取的数据源
 *  2.使用FileReader中的read()方法，读取文件
 *  3.释放资源，关闭流
 */
public class Demo01Reader {

    public static void main(String[] args) throws IOException {
        //1.创建FileReader对象，构造方法中绑定要读取的数据源
        FileReader fr = new FileReader("IO/c.txt");
        //2.使用FileReader中的read()方法，读取文件

        /*//int read() 读取一个字符，并返回
        int len=0;
        while ((len=fr.read()) != -1){
            System.out.println((char)len);
        }*/

        //读取多个字符
        // int read(char[] cbuf) 读取多个字符，将字符读入数组。
        char[] chars=new char[1024];//存储读取到的多个字符
        int len=0;//记录的是每次读取字符的有效个数
        while ((len=fr.read(chars))!=-1){
            //分配一个新的 String
            //String(char[] value)
            //String(char[] value, int offset, int count)
            //offset ： 开始的索引 count：转换的个数
            System.out.println(new String(chars,0,len));
        }

        //3.释放资源，关闭流
        fr.close();

    }
}
