package com.wck.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-10 3:28
 */

/**
 * 自定义类型过滤器
 */
public class MyTypeFilter implements TypeFilter {


    /**
     *
     * @param metadataReader 读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到其他任何类的信息
     * @return
     * @throws IOException
     */

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        //获取到当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        //获取到当前类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        //获取到的当前类的资源信息（类的路径）
        Resource metadataReaderResource = metadataReader.getResource();

        String className=classMetadata.getClassName();
        //打印输出当前类的类名
//        System.out.println("----->>>className:"+className);
            //----->>>className:com.wck.test.IOCTest
            //----->>>className:com.wck.bean.Person
            //----->>>className:com.wck.config.MyTypeFilter
            //----->>>className:com.wck.controller.BookController
            //----->>>className:com.wck.dao.BookDao
            //----->>>className:com.wck.MainTest
            //----->>>className:com.wck.service.BookService

        //如果包含名称中包含er，则扫描通过
        if(className.contains("er")){
            return true;
        }
        return false;
    }
}
