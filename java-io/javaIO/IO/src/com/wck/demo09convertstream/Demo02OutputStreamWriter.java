package com.wck.demo09convertstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author 御香烤翅
 * @create 2020-03-26 15:39
 *
 * java.io.OutputStreamWriter extends Writer
 *  OutputStreamWriter是字符流通向字节流的桥梁：向其写入的字符编码成使用指定的字节charset（把能看懂的变成看不懂的）
 *
 * 继承自父类的共性成员方法：
 *
 *    void write(int c) 写入单个字符。
 *    void write(char[] cbuf) 写入字符数组。
 *    abstract void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,off数组的开始索引,len写的字符个数。
 *    void write(String str) 写入字符串。
 *    void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
 *    void flush() 刷新该流的缓冲。
 *    void close() 关闭此流，但要先刷新它。
 *
 *
 *  构造方法：
 *      OutputStreamWriter(OutputStream out) 创建一个使用默认字符编码的OutputStreamWriter。
 *      OutputStreamWriter(OutputStream out, String charsetName) 创建一个使用命名字符集的OutputStreamWriter。
 *
 *          参数：
 *              OutputStream out：字节输出流，可以用来写转换之后的字节到文件中
 *              String charsetName: 指定编码表名称，不区分大小写，utf-8/UTF-8,不指定使用默认编码，就是IDE的编码
 *
 *  使用步骤：
 *      1 创建OutputStreamWriter对象，构造方法中传入字节输出流和指定编码名称
 *      2 使用OutputStreamWriter对象中的writer方法，把字符转换为字节存储在缓冲区中（并编码）
 *      3 使用OutputStreamWriter对象中的flush方法，把内存缓冲区中数据刷新到文件中（使用字节流写字节的过程）
 *      4.关闭释放资源
 */
public class Demo02OutputStreamWriter {

    public static void main(String[] args) throws IOException {
        writer_gbk();
//        writer_utf_8();
    }

    private static void writer_gbk() throws IOException {

        //1 创建OutputStreamWriter对象，构造方法中传入字节输出流和指定编码名称\
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("IO/gbk.txt"), "gbk");
        //2 使用OutputStreamWriter对象中的writer方法，把字符转换为字节存储在缓冲区中（并编码）
        osw.write("你好中国");
        // 3 使用OutputStreamWriter对象中的flush方法，把内存缓冲区中数据刷新到文件中（使用字节流写字节的过程）
        osw.flush();
        //4.关闭释放资源
        osw.close();
    }

    /**
     * 使用OutputStreamWriter转换流写u-f-8格式的文件
     */
    private static void writer_utf_8() throws IOException {

        //1 创建OutputStreamWriter对象，构造方法中传入字节输出流和指定编码名称\
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("IO/utf-8.txt"), "utf-8");
        //2 使用OutputStreamWriter对象中的writer方法，把字符转换为字节存储在缓冲区中（并编码）
        osw.write("你好中国");
        // 3 使用OutputStreamWriter对象中的flush方法，把内存缓冲区中数据刷新到文件中（使用字节流写字节的过程）
        osw.flush();
        //4.关闭释放资源
        osw.close();
    }
}
