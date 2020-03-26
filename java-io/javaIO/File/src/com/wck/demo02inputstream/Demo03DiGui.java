package com.wck.demo02inputstream;

/**
 * @author 御香烤翅
 * @create 2020-03-24 21:05
 *  n的阶乘：n! = n * (n-1) *...* 3 * 2 * 1
 *  推理得出：n! = n * (n-1)!
 *  **分析**：这与累和类似,只不过换成了乘法运算，学员可以自己练习，需要注意阶乘值符合int类型的范围。
 */
public class Demo03DiGui {

    public static void main(String[] args) {
        int value = getValue(3);
        System.out.println(value);
    }

    private static int getValue(int n) {
        if(n == 1){
            return 1;
        }
        return n*(n-1);
    }
}
