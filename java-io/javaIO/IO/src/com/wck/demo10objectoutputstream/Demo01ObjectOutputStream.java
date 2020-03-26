package com.wck.demo10objectoutputstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author 御香烤翅
 * @create 2020-03-26 17:10
 *
 * java.io.ObjectOutputStream extends java.io.OutputStream
 *  ObjectOutputStream:对象的序列化流
 *  作用：
 *      把对象以流的方式写入到文件中保存
 *
 *  构造方法：
 *      ObjectOutputStream(OutputStream out) 创建一个写入指定的OutputStream的ObjectOutputStream。
 *          参数：
 *              OutputStream out ：字节输出流
 *
 *  特有的成员方法：
 *      void writeObject(Object obj) 将指定的对象写入ObjectOutputStream。
 *
 *  使用步骤：
 *      1 创建ObjectOutputStream对象输出流，构造方法中传入字节输出流
 *      2 使用ObjectOutputStream对象中的writeObject方法，把对象写入到文件中
 *      3 释放关闭资源
 */
public class Demo01ObjectOutputStream {

    public static void main(String[] args) throws IOException {

        //1 创建ObjectOutputStream对象输出流，构造方法中传入字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IO/person.txt"));
        //2 使用ObjectOutputStream对象中的writeObject方法，把对象写入到文件中
        oos.writeObject(new Person("wck",18));
        //3 释放关闭资源
        oos.close();


    }

}
