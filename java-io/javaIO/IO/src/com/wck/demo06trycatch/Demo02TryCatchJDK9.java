package com.wck.demo06trycatch;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 20:49
 *
 * JDK9新特性
 * try的前边可以定义流对象
 * 在try后边的()中可以直接引入流对象的名称(变量名)
 * 在try代码执行完毕之后,流对象也可以释放掉,不用写 finalty格式:
 *    A a=new A();
 *    B b=new B();
 * 	  try(a;b){
 * 	  	可能会产出异常的代码
 * 	  }  catch(异常类变量变量名){
 * 	  异常的处理逻辑
 * 	  }
 *
 */
public class Demo02TryCatchJDK9 {

  /*  public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("IO/f.txt",true);
        try (fw;){

            for (int i = 0; i < 10; i++) {
                fw.write("Hello Word"+i+"\r\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }*/
}
