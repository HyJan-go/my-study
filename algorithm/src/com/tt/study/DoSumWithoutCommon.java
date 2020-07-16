package com.tt.study;

/**
 * @program: algorithm
 * @description: 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 * 使用二进制位运算，这里大概了解一下位运算逻辑
 * 异或运算： 相同为0，不同为1 ^
 * 与运算： 全1为1，其他为0  &
 * 或运算： 全0为0，其他为1  |
 * 移位运算： 左移移位，就是进2倍     <<     1 << 3   变成了8
 *            右移移位，就是减少一半  >>
 *
 *    解题思路：先异或，然后与进行移位，继续相加，到退出条件为止。
 * @author: HyJan
 * @create: 2020-07-10 11:03
 **/
public class DoSumWithoutCommon {

    public static int add(int num1,int num2){
        int sum,mark;
        // 只要还有进位都得算
        do {
            sum = num1 ^ num2;
            mark = (num1 & num2) << 1;
            num1 = sum;
            num2 = mark;
        }while (mark != 0);
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(add(5,3));
    }
}
