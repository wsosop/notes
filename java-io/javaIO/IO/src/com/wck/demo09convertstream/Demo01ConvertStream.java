package com.wck.demo09convertstream;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-26 15:07
 */
public class Demo01ConvertStream {


    public static void main(String[] args) throws IOException {
        //Gbk格式的txt文本使用utf-8 读取会产生乱码，编码方式不同就会乱码
        FileReader fr = new FileReader("IO/hahah.txt");
        int len=0;
        while ((len=fr.read())!=-1){
            System.out.println((char)len);
        }
        fr.close();
    }

}
