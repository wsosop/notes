package com.wck.demo02inputstream;

import java.io.File;

/**
 * @author 御香烤翅
 * @create 2020-03-24 21:09
 */
public class Demo04DiGui {


    public static void main(String[] args) {
        getAllFile(new File("ioTestDir"));
    }

    private static void getAllFile(File dir) {
        System.out.println(dir);
        if(!dir.exists()){
            System.out.println("不存在的目录");
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                getAllFile(file);
            }else {
                System.out.println(file);
            }
        }


    }
}
