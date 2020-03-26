package com.wck.demo01outputstream.file;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 17:59
 *
 * 路径
 *      绝对路径：是一个完整的路径
 *      相对路径：是一个简化路径
 * 注意：路径不区分大小写
 */
public class Demo02File {
    public static void main(String[] args) {

//        show01();
//        show02("d:","a.txt");
        
        show03();


    }

    /**
     * File(File parent, String child)
     * 从父抽象路径名和子路径名字符串创建新的 File实例。
     *
     */
    private static void show03() {

        File parent =new File("d:");
        File file = new File(parent, "a.txt");
        System.out.println(file);

    }

    /**
     * File(String parent, String child)
     * 从父路径名字符串和子路径名字符串创建新的 File实例。
     *      String parent 父路径
     *      String child 子路径
     *
     *
     */

    private static void show02(String parent, String child) {

        File file = new File(parent, child);
        System.out.println(file);
    }

    /**
     * File(String pathname) 通过将给定路径名称字符串转换为抽象路径来创建一个新的File类
     * 参数： String pathname ：
     *          字符串的路径名称
     *          路径可以是以文件结尾，也可以是文件夹结尾
     *          路径可以是相对路径，也可以是绝对路径
     *          路径可以是存在的，也可以是不存在的
     *          创建File对象，只是把字符串路径转换为File对象，不考虑路径的真假情况
     */
    private static void show01() {
        //文件
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\b.txt");
        System.out.println(file);

        //文件夹
        File file2 = new File("D:\\ideaProject\\java-io\\IOTest\\");
        System.out.println(file2);

        //相对文件
        File file3 = new File("c.txt");
        System.out.println(file3);
    }


}
