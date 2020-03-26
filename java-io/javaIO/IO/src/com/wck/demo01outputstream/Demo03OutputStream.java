package com.wck.demo01outputstream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 0:26
 *
 * 追加、续写，使用两个参数的构造方法
 * FileOutputStream(String name, boolean append) 创建文件输出流以指定的名称写入文件。
 * FileOutputStream(File file, boolean append) 创建文件输出流以写入由指定的 File对象表示的文件。
 *  参数：
 *      String name 、 File file ：写入数据的目的地
 *      boolean append：追加写开关 true :创建对象不会覆盖原文件，继续在原来的文件后面追加写
 *                                false:创建一个新文件，覆盖掉原来的文件
 *
 *  写换行：写换行符号
 *         windows:\r\n
 *         linux : /n
 *         mac : /r
 *
 *
 */
public class Demo03OutputStream {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("IO/c.txt", true);
        //换行
        for (int i = 0; i < 10; i++) {
            fos.write("你好".getBytes());
            fos.write("\r\n".getBytes());
        }
        fos.close();
    }
}
