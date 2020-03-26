package com.wck.demo03;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 22:10
 * listFiles(FileFilter filter)
 * 返回一个抽象路径名(File)数组，表示由此抽象路径名表示的满足指定过滤器的目录中的文件和目录。
 *      java.io.FileFilter接口
 *          作用：用来过滤文件（File对象）
 *          抽象方法：用来过滤文件的方法
 *             boolean accept(File pathname)测试指定的抽象路径名是否应包含在路径名列表中
 *                  参数 File pathname：就是使用listFiles方法遍历目录，得到的每一个文件对象
 *
 * list(FilenameFilter filter)
 * 返回一个字符串数组，命名由此抽象路径名表示的目录中满足指定过滤器的文件和目录。
 *      java.io.FilenameFilter接口 ：用于实现此接口的类的实例用于过滤文件名。
 *          作用：用来过滤文件名称
 *          抽象方法：用来过滤文件的方法
 *              boolean accept(File dir, String name)测试指定文件是否应包含在文件列表中。
 *                  参数
 *                      File dir：构造方法中传递的被遍历的目录
 *                      String name：使用listFiles方法遍历目录，获取的每一个文件/文件夹的名称
 *  注意：两个接口是没有实现类的，需要我们自己写实现类，重写过滤的方法accept，在方法中自己定义过滤的规则
 */
public class Demo01Filter {

    public static void main(String[] args) {
        getAllFile(new File("ioTestDir"));
    }

    private static void getAllFile(File dir) {
//        System.out.println(dir);
        if(!dir.exists()){
            System.out.println("不存在的目录");
            return;
        }
        File[] files = dir.listFiles(new FileFilterImpl());
        for (File file : files) {
            if(file.isDirectory()){
                getAllFile(file);
            }else {
                System.out.println(file);
            }
        }


    }
}
