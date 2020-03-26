package com.wck.demo02inputstream;

/**
 * @author 御香烤翅
 * @create 2020-03-24 20:44
 *
 * 使用递归计算 1~n 的和
 */
public class Demo02DiGui {

    public static void main(String[] args) {

        int sum = sum(3);
        System.out.println(sum);
    }

    /**
     * 定义 一个方法，计算  1~n的和
     *
     *      1+2+3+4+...+n
     * 等价于
     *      n+(n-1)+(n-2)+...+1
     *
     * 使用递归必须明确
     *      1.递归的结束条件
     *            获取到1 的时候结束
     *      2.递归的目的
     *            获取下一个被加的数字（n-1）
     */

    public static int sum(int n){
        //判断到1的时候结束
        if(n == 1){
            return 1;
        }
        //获取下一个被加的数字 (n-1)
        return n+sum(n-1);
    }
}
