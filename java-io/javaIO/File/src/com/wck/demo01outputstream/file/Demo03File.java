package com.wck.demo01outputstream.file;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 18:52
 *
 *  File类获取功能的方法
 * public String getAbsolutePath() ：返回此File的绝对路径名字符串。
 *
 * public String getPath() ：将此File转换为路径名字符串。
 *
 * public String getName() ：返回由此File表示的文件或目录的名称。
 *
 * public long length() ：返回由此File表示的文件的长度。
 */

public class Demo03File {

    public static void main(String[] args) {
        
        show04();
        System.out.println("-------------------");
        show03();
        System.out.println("-------------------");
        show02();
        System.out.println("-------------------");
        show01();
        
    }

    /**
     * public long length() ：返回由此File表示的文件的长度。
     * 获取的是构造方法指定的文件的大小，以字节为单位
     * 注意：
     *      文件夹是没有大小概念的，不能获取文件夹的大小
     *      如果构造方法中给出的文件路径不存在，则 length() 返回 0
     */
    private static void show04() {
        //存在 不存在的返回 0
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\a.txt");
        long length = file.length();
        System.out.println(length); // 4

        //文件夹
        File file2 = new File("D:\\ideaProject\\java-io\\IOTest");
        long length1 = file2.length();
        System.out.println(length1); //0

    }

    /**
     * public String getName() ：返回由此File表示的文件或目录的名称。
     * 获取的就是构造方法传递路径的结尾部分（文件、文件夹）
     */
    private static void show03() {

        File file = new File("D:\\ideaProject\\java-io\\IOTest\\b.txt");
        File file1 = new File("D:\\ideaProject\\java-io\\IOTest");
        String name = file.getName();
        String name1 = file1.getName();
        System.out.println(name);//b.txt
        System.out.println(name1);//IOTest
    }

    /**
     *  public String getPath() ：将此File转换为路径名字符串
     *  原样输出，传递的是什么样，就输出什么样
     */
    private static void show02() {
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\b.txt");
        File file1 = new File("a.txt");
        String path = file.getPath();
        String path1 = file1.getPath();
        System.out.println(path);//D:\ideaProject\java-io\IOTest\b.txt
        System.out.println(path1);//a.txt

        //toString方法调用的就是 getPath()方法
        System.out.println(file.toString());//D:\ideaProject\java-io\IOTest\b.txt
    }

    /**
     * public String getAbsolutePath() ：返回此File的绝对路径名字符串。
     * 获取绝对路径
     */
    private static void show01() {
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\b.txt");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);//D:\ideaProject\java-io\IOTest\b.txt

        File file1 = new File("a.txt");
        String absolutePath1 = file1.getAbsolutePath();
        System.out.println(absolutePath1);//D:\ideaProject\java-io\javaIO\a.txt
    }
}
