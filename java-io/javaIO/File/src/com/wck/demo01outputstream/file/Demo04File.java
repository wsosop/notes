package com.wck.demo01outputstream.file;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 19:20
 *
 *
 * 判断功能的方法
 * public boolean exists() ：此File表示的文件或目录是否实际存在。
 * public boolean isDirectory() ：此File表示的是否为目录。
 * public boolean isFile() ：此File表示的是否为文件。
 */
public class Demo04File {

    public static void main(String[] args) {
        show02();
    }

    /**
     * public boolean isDirectory() ：此File表示的是否为目录。
     *  判断是否是以文件夹结尾 是 true 不是 false
     * public boolean isFile() ：此File表示的是否为文件。
     *  判断是否是以文件结尾 是 true 不是 false
     *
     *  两个判断如果路径都不存在，则返回 是 false
     *
     */
    private static void show02() {
        File file = new File("D:\\ideaProject\\java-io\\IOTest");
        boolean b = file.isDirectory();
        System.out.println(b);//true

        //不存在的路径
        File file1 = new File("D:\\ideaProject\\java-io\\IOTest\\c.txt");
        boolean b1 = file1.isDirectory();
        System.out.println(b1);//false

    }

    /**
     * public boolean exists() ：此File表示的文件或目录是否实际存在。
     *  用于判断构造方法中的路径是否存在
     *  存在 true
     *  不存在 false
     *
     */
    private static void show01() {
        //存在 的路径
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\a.txt");
        boolean exists = file.exists();
        System.out.println(exists);//true

        //不存在的路径
        File file1 = new File("D:\\ideaProject\\java-io\\IOTest\\c.txt");
        boolean exists1 = file1.exists();
        System.out.println(exists1);//false


    }

}
