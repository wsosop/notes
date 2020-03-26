package com.wck.demo09convertstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 御香烤翅
 * @create 2020-03-26 16:16
 *
 * java.io.InputStreamReader extends java.io.Reader
 *  InputStreamReader:是从字节流通向字符流的桥梁：它读取字节，并使用指定的charset将其解码为字符（把看不懂的变成能看懂的）
 *
 *  共性的成员方法：
 *      int read() 读取一个字符，并返回
 *      int read(char[] cbuf) 读取多个字符，将字符读入数组。
 *      abstract void close() 关闭流并释放与之相关联的任何系统资源。
 *
 *  构造方法：
 *      InputStreamReader(InputStream in) 创建一个使用默认字符集的InputStreamReader。
 *      InputStreamReader(InputStream in, String charsetName) 创建一个使用命名字符集的InputStreamReader。
 *    参数：
 *      InputStream in：字节输入流，用来读取文件中保存的字节
 *      String charsetName: 指定编码表名称，不区分大小写，utf-8/UTF-8,不指定使用默认编码，就是IDE的编码
 *
 *    使用步骤：
 *      1 创建InputStreamReader对象，构造方法中传递字节输入流和指定的编码表名称
 *      2 使用创建InputStreamReader对象的read读取文件
 *      3 释放关闭资源
 *
 *    注意事项：
 *      构造方法中指定的编码表名称要和文件的编码相同，否则会发生乱码
 *
 */
public class Demo03InputStreamReader {

    public static void main(String[] args) throws IOException {
        //1 创建InputStreamReader对象，构造方法中传递字节输入流和指定的编码表名称
        InputStreamReader isr = new InputStreamReader(new FileInputStream("IO/utf-8.txt"), "utf-8");
        //2 使用创建InputStreamReader对象的read读取文件

        int len=0;

        while ((len=isr.read())!=-1){
            System.out.print((char)len);
        }
        //3 释放关闭资源
        isr.close();
    }

}
