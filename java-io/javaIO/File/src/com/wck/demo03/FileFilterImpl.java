package com.wck.demo03;

import java.io.File;
import java.io.FileFilter;

/**
 * @author 御香烤翅
 * @create 2020-03-24 22:24
 * 创建过滤器FileFilter的实现类，重写accept，定义过滤规则
 */
public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
//        System.out.println("pathname:"+pathname);
        if(pathname.isDirectory()){
            return true;
        }
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}
