package com.tt.study;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 * 示例1
 * 输入
 * +2147483647
 * 1a33
 * 输出
 * 2147483647
 * 0
 * @author: HyJan
 * @create: 2020-07-23 15:54
 **/
public class StringToNum {

    /**
     * 字符串转数字
     *
     * @param str
     * @return
     */
    public static int StrToInt(String str) {
        // 判断输入
        if (Objects.equals("", str) || str.length() < 1 || Objects.equals(str.trim(), "")) {
            return 0;
        }
        int result = 0;
        // 默认设置传入的str为一个正数
        boolean isPositiveNum = true;
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                if (str.charAt(i) == '-') {
                    // 说明是一个负数
                    isPositiveNum = false;
                    continue;
                }
                if (str.charAt(i) == '+') {
                    continue;
                }
                // char是有ascall码的，所以不能这样直接转成int,要使用这种减去0所在的ascall码的差来就得
                int j = (int) str.charAt(i) - (int) ('0');
                // 结果是在0到9之间的各个数字，才有可能是数字，否则不是数字
                if (j >= 0 && j <= 9) {
                    result = j;
                } else {
                    return 0;
                }
            } else {
                // char是有ascall码的，所以不能这样直接转成int,要使用这种减去0所在的ascall码的差来就得
                int j = (int) str.charAt(i) - (int) ('0');
                // 结果是在0到9之间的各个数字，才有可能是数字，否则不是数字
                if (j >= 0 && j <= 9) {
                    result = result * 10 + j;
                } else {
                    return 0;
                }
            }
        }
        if (!isPositiveNum) {
            return -result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(StrToInt("1256"));
        System.out.println(StrToInt("-1248"));
        System.out.println(StrToInt(""));
        System.out.println(StrToInt("+1259"));
        System.out.println(StrToInt("+1259f"));
    }
}
