package com.wck.demo06trycatch;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-25 20:40
 *
 * JDK7的新特性
 * 在try的后边可以增加一个(),在括号中可以定义流对象
 * 那么这个流对象的作用域就在try中有效
 * try中的代码执行完毕,会自动把流对象释放,不用写 finally
 * 格式:
 * 	try(定义流对象;定义流对象...){
 * 		可能会产出异常的代码
 * 	}  catch(异常类变量变量名){
 * 	异常的处理逻辑
 * 	}
 *
 */
public class Demo01TryCatchJDK7 {

    public static void main(String[] args) {
        try ( FileWriter fw = new FileWriter("IO/f.txt",true);){
            for (int i = 0; i < 10; i++) {
                fw.write("Hello Word"+i+"\r\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
