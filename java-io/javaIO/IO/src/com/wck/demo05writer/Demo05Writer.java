package com.wck.demo05writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 19:32
 *
 *
 */
public class Demo05Writer {

    public static void main(String[] args){
        FileWriter fw=null;
        try {
            fw = new FileWriter("z:/IO/f.txt",true);
            for (int i = 0; i < 10; i++) {
                fw.write("Hello Word"+i+"\r\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
           if(fw != null){
               try {
                   fw.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }


    }
}
