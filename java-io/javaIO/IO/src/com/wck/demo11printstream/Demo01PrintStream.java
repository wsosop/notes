package com.wck.demo11printstream;

/**
 * @author 御香烤翅
 * @create 2020-03-26 18:19
 * java.io.PrintStream  extends java.io.OutputStream
 *  PrintStream:为其他输出流添加了功能，即能够方便地打印各种数据值的表示。
 *
 *  继承自父类的成员方法：
 *
 * - public void close() ：关闭此输出流并释放与此流相关联的任何系统资源。
 * - public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
 * - public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
 * - public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
 * - public abstract void write(int b) ：将指定的字节输出流。
 *
 *  PrintStream特点：
 *      1 只负责数据的输出，不负责数据的读取
 *      2 PrintStream从不抛出IOException错误
 *      3 有特殊的方法：
 *          print()和println()
 *          void print(任意类型的值)
 *          void println(任意类型的值并换行)
 *
 *  构造方法：
 *      PrintStream(File file) 输出的目的地是一个文件
 *      PrintStream(OutputStream out) 输出的目的地是一个字节输出流
 *      PrintStream(String fileName) 输出的目的地是一个文件的路径
 *
 */
public class Demo01PrintStream {
}
