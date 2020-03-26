package com.wck.demo10objectoutputstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author 御香烤翅
 * @create 2020-03-26 17:32
 *
 *  java.io.ObjectInputStream  extends java.io.InputStream
 *      ObjectInputStream:对象的输入流，对象的反序列化流
 *          作用：把文件中保存的对象，以流的方式读出来使用
 *
 *  构造方法：
 *      ObjectInputStream(InputStream in) 创建从指定的InputStream读取的ObjectInputStream。
 *          参数：
 *             InputStream in ：字节输入流
 *  特有的成员方法：
 *      Object readObject() 从ObjectInputStream读取一个对象。
 *
 *  使用步骤：
 *      1 创建ObjectInputStream对象，传入字节输入流
 *      2 使用ObjectInputStream对象的readObject()方法，读取保存对象的文件
 *      3 释放关闭资源
 *      4 使用读取出来的对象（打印）
 *
 */
public class Demo02ObjectInputStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1 创建ObjectInputStream对象，传入字节输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("IO/person.txt"));
        //2 使用ObjectInputStream对象的readObject()方法，读取保存对象的文件
        Object object = ois.readObject();
        //3 释放关闭资源
        ois.close();
        //4 使用读取出来的对象（打印）
        Person p= (Person) object;
        System.out.println(p.toString());
    }
}
