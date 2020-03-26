package com.wck.demo01outputstream.file;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 20:21
 *
 * public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。
 *
 * public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
 *
 * 注意
 *    如果给出的目录不存在，则抛出空指针异常
 *    如果给出的目录是一个目录，则抛出空指针异常
 */
public class Demo06File {

    public static void main(String[] args) {

        show02();
    }

    /**
     * public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
     * 遍历给出的目录，会获取目录中所有文件夹、文件的名称，把获取到的多个名称存储到一个File类型的数组中
     *  注意
     *     如果给出的目录不存在，则抛出空指针异常
     *     如果给出的目录是一个目录，则抛出空指针异常
     */
    private static void show02() {
        File file1 = new File("ioTestDir");
        File[] list = file1.listFiles();
        for (File f : list) {
            System.out.println(f);
        }

    }

    /**
     * public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。
     *
     * 遍历给出的目录，会获取目录中所有文件夹、文件的名称，把获取到的多个名称存储到一个String类型的数组中
     * 可以获取隐藏的文件和文件夹
     *  注意
     *     如果给出的目录不存在，则抛出空指针异常
     *     如果给出的目录是一个目录，则抛出空指针异常
     */
    private static void show01() {
        File file1 = new File("ioTestDir");
        String[] list = file1.list();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
