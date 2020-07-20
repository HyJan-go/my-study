package com.tt.study;

/**
 * @program: algorithm
 * @description: 循环左移算法
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，
 * 即“XYZdefabc”。是不是很简单？OK，搞定它！
 * @author: HyJan
 * @create: 2020-07-20 15:55
 **/
public class ROL {

    /**
     * 左移多少位就是将前面多少个放到字符串的后面
     * @param str
     * @param n
     * @return
     */
    public static String LeftRotateString(String str,int n) {
        if ("".equals(str) || "".equals(str.trim()) || str.length() < 1 || n < 1){
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        String substring = str.substring(0, n);
        stringBuilder.append(substring);
        return stringBuilder.toString().substring(n);
    }

    public static void main(String[] args) {
        System.out.println(LeftRotateString("fahdfjdl",4));
    }
}
