package com.wck.demo01outputstream.file;

import java.io.File;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-24 19:34
 *
 * public boolean createNewFile() ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
 * public boolean delete() ：删除由此File表示的文件或目录。
 * public boolean mkdir() ：创建由此File表示的目录。
 * public boolean mkdirs() ：创建由此File表示的目录，包括任何必需但不存在的父目录。
 * 
 */
public class Demo05File {

    public static void main(String[] args) throws IOException {
        show03();
    }

    /**
     * public boolean delete() ：删除由此File表示的文件或目录。
     *  既可以删除文件，也可以删除文件夹
     *  true:文件、文件夹删除成功 返回true
     *  false：
     *      文件夹中有内容，不会删除，返回false
     *      构造方法中的路径不存在，返回 false
     *
     *  delete 直接在硬盘上删除，不走回收站
     */
    private static void show03() {
        File file1 = new File("ioTestDir/a/b/c/abc.txt");
        boolean delete = file1.delete();
        System.out.println(delete);

    }

    /**
     *  public boolean mkdir() ：创建由此File表示的目录
     *      创建单级的空文件夹
     *  public boolean mkdirs() ：创建由此File表示的目录，包括任何必需但不存在的父目录。
     *      既可以创建单级的文件夹，也可以创建多级的文件夹
     *  创建文件夹 如果不存在 则创建 返回 true
     *  创建文件夹 如果存在 则不创建 返回 false
     *
     *   注意事项：
     *      构造方法中给出的路径不存在创建返回false
     *      只能创建文件夹，不能创建文件
     *      创建的路径必须存在，否则会报异常
     */
    private static void show02() {

        //文件夹不存在的情况下，创建成功
        File file = new File("ioTestDir");
        boolean mkdir = file.mkdir();
        System.out.println(mkdir);//true

        //创建多级文件夹，且文件夹 不存在
        File file1 = new File("ioTestDir/a/b/c");
        boolean mkdirs = file1.mkdirs();
        System.out.println(mkdirs);//true

        //这个也是为true 创建了一个 abc.txt的文件夹
        File file2 = new File("ioTestDir/a/b/c/abc.txt");
        boolean mkdirs2 = file2.mkdirs();
        System.out.println(mkdirs2);//true

        //不存在的路径,也会创建
        File file3 = new File("aaar/ccc.txt");
        boolean mkdirs3 = file3.mkdirs();
        System.out.println(mkdirs3);//true


    }

    /**
     * public boolean createNewFile() ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
     *  创建文件 如果不存在 则创建 返回 true
     *  创建文件 如果存在 则不创建 返回 false
     *
     *  注意：
     *      此方法只能创建文件，不能创建文件夹
     *      创建文件的路径必须存在，否则会抛出异常
     */
    private static void show01() throws IOException {

        //路径正确，不存在的文件
        File file = new File("D:\\ideaProject\\java-io\\IOTest\\1.txt");
        boolean newFile = file.createNewFile();
        System.out.println(newFile);//true

        //再次创建
        File file1 = new File("D:\\ideaProject\\java-io\\IOTest\\1.txt");
        boolean newFile2 = file1.createNewFile();
        System.out.println(newFile2);//false
    }
}
