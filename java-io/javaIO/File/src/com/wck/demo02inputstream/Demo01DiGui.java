package com.wck.demo02inputstream;

/**
 * @author 御香烤翅
 * @create 2020-03-24 20:42
 */
public class Demo01DiGui {

    public static void main(String[] args) {
        // a();
        b(1);
    }

    /*
     * 3.构造方法,禁止递归
     * 编译报错:构造方法是创建对象使用的,不能让对象一直创建下去
     */
    public Demo01DiGui() {
        //Demo01DiGui();
    }


    /*
     * 2.在递归中虽然有限定条件，但是递归次数不能太多。否则也会发生栈内存溢出。
     * 4993
     * 	Exception in thread "main" java.lang.StackOverflowError
     */
    private static void b(int i) {
        System.out.println(i);
        //添加一个递归结束的条件,i==5000的时候结束
        if(i==5000){
            return;//结束方法
        }
        b(++i);
    }

    /*
     * 1.递归一定要有条件限定，保证递归能够停止下来，否则会发生栈内存溢出。 Exception in thread "main"
     * java.lang.StackOverflowError
     */
    private static void a() {
        System.out.println("a方法");
        a();
    }
}
