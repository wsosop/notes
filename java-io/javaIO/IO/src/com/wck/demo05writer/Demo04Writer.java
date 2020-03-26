package com.wck.demo05writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 19:32
 *
 * 续写和换行
 *  续写：追加写，使用两个参数的构造方法
 *
 *  FileWriter(String fileName, boolean append) 构造一个FileWriter对象，给出一个带有布尔值的文件名，表示是否附加写入的数据。
 *  FileWriter(File file, boolean append) 给一个File对象构造一个FileWriter对象。
 *      参数：
 *          String fileName、File file：写入数据的目的地
 *          boolean append: 续写开关 true:不会创建新的文件覆盖原文件，可以续写，false：创建新的文件，覆盖原文件
 *      换行：
 *          Windows：\r\n
 *          linux:/n
 *          mac:/r
 *
 *
 *
 */
public class Demo04Writer {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("IO/f.txt",true);
        for (int i = 0; i < 10; i++) {
            fw.write("Hello Word"+i+"\r\n");
        }

        fw.close();
    }
}
